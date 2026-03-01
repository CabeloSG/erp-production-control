package com.autoflex.leandro.production.application.dto;

public class RawMaterialResponseDTO {

    private Long id;
    private String code;
    private String name;
    private Double quantityInStock;

    public RawMaterialResponseDTO(Long id, String code, String name, Double quantityInStock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Double getQuantityInStock() { return quantityInStock; }
}
