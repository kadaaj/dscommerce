package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.User;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}