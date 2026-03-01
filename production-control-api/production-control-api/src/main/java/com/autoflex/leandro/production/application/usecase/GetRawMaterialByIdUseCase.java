package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetRawMaterialByIdUseCase {

    private final RawMaterialRepository repository;

    public GetRawMaterialByIdUseCase(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public RawMaterial execute(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Matéria-prima não encontrada com o ID especificado: " + id)
                );
    }
}