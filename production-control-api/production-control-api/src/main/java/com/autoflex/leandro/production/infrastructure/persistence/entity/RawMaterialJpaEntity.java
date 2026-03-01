package com.autoflex.leandro.production.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_materials")
public class RawMaterialJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @Column(name = "quantity_in_stock")
    private Double quantityInStock;

    public RawMaterialJpaEntity() {}

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

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