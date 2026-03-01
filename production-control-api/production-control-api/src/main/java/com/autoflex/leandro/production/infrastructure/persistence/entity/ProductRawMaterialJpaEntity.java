package com.autoflex.leandro.production.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_raw_material")
public class ProductRawMaterialJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductJpaEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterialJpaEntity rawMaterial;

    @Column(nullable = false)
    private Double quantityRequired;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ProductJpaEntity getProduct() { return product; }
    public void setProduct(ProductJpaEntity product) { this.product = product; }

    public RawMaterialJpaEntity getRawMaterial() { return rawMaterial; }
    public void setRawMaterial(RawMaterialJpaEntity rawMaterial) { this.rawMaterial = rawMaterial; }

    public Double getQuantityRequired() { return quantityRequired; }
    public void setQuantityRequired(Double quantityRequired) { this.quantityRequired = quantityRequired; }
}