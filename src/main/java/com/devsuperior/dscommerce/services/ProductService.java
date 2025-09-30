package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id){

        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDto dto = new ProductDto(product);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDto(x));
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {

        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity = repository.save(entity);

        return new ProductDto(entity);
    }

}
