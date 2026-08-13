package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO
{
    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(Instant timestamp, Integer status, String error,
                              String path, List<FieldMessageDTO> errors)
    {
        super(timestamp, status, error, path);
        this.errors = errors;
    }

    public List<FieldMessageDTO> getErrors()
    {
        return errors;
    }

    public void addError(String fieldName, String message)
    {
        errors.add(new FieldMessageDTO(fieldName, message));
    }
}
