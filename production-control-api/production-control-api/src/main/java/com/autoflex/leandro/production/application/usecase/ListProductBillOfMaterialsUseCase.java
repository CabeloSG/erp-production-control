package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.domain.repository.ProductRawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductBillOfMaterialsUseCase {

    private final ProductRawMaterialRepository repository;

    public ListProductBillOfMaterialsUseCase(
            ProductRawMaterialRepository repository
    ) {
        this.repository = repository;
    }

    public List<ProductRawMaterial> execute(Long productId) {
        return repository.findByProductId(productId);
    }
}
