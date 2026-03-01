package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.repository.ProductBillOfMaterialRepository;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteBillOfMaterialUseCase {

    private final ProductBillOfMaterialRepository repository;

    public DeleteBillOfMaterialUseCase(
            ProductBillOfMaterialRepository repository
    ) {
        this.repository = repository;
    }

    public void execute(Long id) {

        var bom = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("BOM não encontrado"));

        repository.delete(bom);
    }
}