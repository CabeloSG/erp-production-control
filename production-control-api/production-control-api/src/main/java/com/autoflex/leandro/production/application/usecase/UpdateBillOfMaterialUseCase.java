package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.UpdateBillOfMaterialRequestDTO;
import com.autoflex.leandro.production.domain.repository.ProductBillOfMaterialRepository;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateBillOfMaterialUseCase {

    private final ProductBillOfMaterialRepository repository;

    public UpdateBillOfMaterialUseCase(
            ProductBillOfMaterialRepository repository
    ) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id, UpdateBillOfMaterialRequestDTO dto) {

        var materiais = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lista de materiais não encontrado"));

                if (dto.quantityRequired() == null ||
                    dto.quantityRequired() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida");
            }

            repository.updateQuantity(id, dto.quantityRequired());
        }
}