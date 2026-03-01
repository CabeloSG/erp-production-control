package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import com.autoflex.leandro.production.infrastructure.exception.DuplicateResourceException;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UpdateRawMaterialUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateRawMaterialUseCase.class);
    private final RawMaterialRepository rawMaterialRepository;

    public UpdateRawMaterialUseCase(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public RawMaterial execute(Long id, RawMaterial updatedData) {

        RawMaterial existing = rawMaterialRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Matéria-prima não encontrada com o ID: " + id
                        )
                );

        // Validação de código duplicado (somente se mudou)
        if (!existing.getCode().equals(updatedData.getCode())
                && rawMaterialRepository.existsByCode(updatedData.getCode())) {

            throw new DuplicateResourceException(
                    "Já existe matéria-prima com código: " + updatedData.getCode()
            );
        }

        log.info("ANTES UPDATE -> {}", existing.getCode());
        // Atualiza os dados
        existing.setCode(updatedData.getCode());
        existing.setName(updatedData.getName());
        existing.setQuantityInStock(updatedData.getQuantityInStock());

        log.info("DEPOIS UPDATE -> {}", existing.getCode());

        return rawMaterialRepository.save(existing);
    }
}