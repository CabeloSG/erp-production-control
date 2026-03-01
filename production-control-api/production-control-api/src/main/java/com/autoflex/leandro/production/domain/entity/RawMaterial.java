package com.autoflex.leandro.production.domain.entity;

public class RawMaterial {

    private Long id;
    private String code;
    private String name;
    private Double quantityInStock;

    public RawMaterial() {}

    public RawMaterial(Long id, String code, String name, Double quantityInStock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    // IMPORTANTE (opcional mas recomendado)
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    // CORRIGIDO
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    // CORRIGIDO
    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}