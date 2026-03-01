package com.autoflex.leandro.production.domain.entity;

public class ProductRawMaterial {

    private Long id;
    private Product product;
    private RawMaterial rawMaterial;
    private Double quantityRequired;

    public ProductRawMaterial() {}

    public ProductRawMaterial(Long id, Product product, RawMaterial rawMaterial, Double quantityRequired) {
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantityRequired = quantityRequired;
    }



    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Double getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(Double quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
}