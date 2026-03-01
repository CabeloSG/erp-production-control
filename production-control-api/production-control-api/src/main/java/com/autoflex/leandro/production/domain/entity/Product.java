package com.autoflex.leandro.production.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private Long id;
    private String code;
    private String name;
    private BigDecimal value;

    private List<ProductRawMaterial> materials = new ArrayList<>();

    public Product() {}

    public Product(Long id, String code, String name, BigDecimal value) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
    }

    // comportamento do domínio
    public void addRawMaterial(ProductRawMaterial material) {
        this.materials.add(material);
    }

    public List<ProductRawMaterial> getMaterials() {
        return materials;
    }

    // getters setters normais

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() { return value;  }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}