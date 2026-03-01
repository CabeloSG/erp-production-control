package com.autoflex.leandro.production.application.dto;

public class AssociateRawMaterialRequestDTO {

    private Long rawMaterialId;
    private Double quantity;

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(Long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

}
