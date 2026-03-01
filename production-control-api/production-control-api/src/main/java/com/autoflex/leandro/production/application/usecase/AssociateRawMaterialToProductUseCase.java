package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.*;
import com.autoflex.leandro.production.domain.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AssociateRawMaterialToProductUseCase {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    public AssociateRawMaterialToProductUseCase(
            ProductRepository productRepository,
            RawMaterialRepository rawMaterialRepository,
            ProductRawMaterialRepository productRawMaterialRepository
    ) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.productRawMaterialRepository = productRawMaterialRepository;
    }

    public ProductRawMaterial execute(Long productId, Long rawMaterialId, Double quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId)
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        ProductRawMaterial association =
                new ProductRawMaterial(null, product, rawMaterial, quantity);

        return productRawMaterialRepository.save(association);
    }
}