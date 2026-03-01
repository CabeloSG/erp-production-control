package com.autoflex.leandro.production.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RawMaterialRequestDTO {

    @NotBlank(message = "O código é obrigatório.")
    private String code;

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private Double quantityInStock;

    public String getCode() { return code; }
    public String getName() { return name; }
    public Double getQuantityInStock() { return quantityInStock; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setQuantityInStock(Double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}