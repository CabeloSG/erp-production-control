package com.autoflex.leandro.production.application.dto;

import java.math.BigDecimal;

public class ProductResponseDTO {

    private Long id;
    private String code;
    private String name;
    private BigDecimal value;

    public ProductResponseDTO(Long id, String code, String name, BigDecimal value) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public BigDecimal getValue() { return value; }
}