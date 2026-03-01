package com.autoflex.leandro.production.domain.entity;

public class ProductionCapacityResult {

    private int capacity;
    private RawMaterial  limitingRawMaterial;

    public ProductionCapacityResult() {}

    public ProductionCapacityResult(int capacity, RawMaterial limitingRawMaterial) {
        this.capacity = capacity;
        this.limitingRawMaterial = limitingRawMaterial;
    }

    public int getCapacity() {
        return capacity;
    }

    public RawMaterial getLimitingRawMaterial() {
        return limitingRawMaterial;
    }
}
