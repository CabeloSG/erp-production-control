package com.autoflex.leandro.production.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductRequestDTO {

    @NotBlank(message = "O código é obrigatório.")
    private String code;

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotNull(message = "O valor é obrigatório.")
    @Positive(message = "O valor deve ser maior que zero.")
    private BigDecimal value;

    public ProductRequestDTO() {}

    public ProductRequestDTO(String code, String name, BigDecimal value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public BigDecimal getValue() { return value; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setValue(BigDecimal value) { this.value = value; }
}