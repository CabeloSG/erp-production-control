package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import com.autoflex.leandro.production.infrastructure.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

@Service
public class CreateRawMaterialUseCase {

    private final RawMaterialRepository repository;

    public CreateRawMaterialUseCase(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public RawMaterial execute(RawMaterial rawMaterial) {

        // VALIDAÇÃO DE DUPLICIDADE
        if (repository.existsByCode(rawMaterial.getCode())) {
            throw new DuplicateResourceException(
                    "Já existe matéria-prima com código: " + rawMaterial.getCode()
            );
        }

        return repository.save(rawMaterial);
    }
}