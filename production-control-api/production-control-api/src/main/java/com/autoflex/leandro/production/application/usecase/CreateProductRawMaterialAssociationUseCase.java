package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.ProductRawMaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductRawMaterialAssociationUseCase {

    private final ProductRawMaterialRepository repository;

    public CreateProductRawMaterialAssociationUseCase(ProductRawMaterialRepository repository) {
        this.repository = repository;
    }

    public ProductRawMaterial execute(Product product, RawMaterial rawMaterial, Double quantityRequired) {
        ProductRawMaterial association = new ProductRawMaterial(
                null,
                product,
                rawMaterial,
                quantityRequired
        );
        return repository.save(association);
    }
}