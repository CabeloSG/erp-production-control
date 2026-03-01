package com.autoflex.leandro.production.application.dto;

public class ProductionCapacityResponseDTO {

    private Long productId;
    private String productCode;
    private String productName;
    private int capacity;
    private RawMaterialLimitDTO limitingRawMaterial;

    public ProductionCapacityResponseDTO(
            Long productId,
            String productCode,
            String productName,
            int capacity,
            RawMaterialLimitDTO limitingRawMaterial) {

        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.capacity = capacity;
        this.limitingRawMaterial = limitingRawMaterial;
    }

    public Long getProductId() { return productId; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public int getCapacity() { return capacity; }
    public RawMaterialLimitDTO getLimitingRawMaterial() {
        return limitingRawMaterial;
    }
}