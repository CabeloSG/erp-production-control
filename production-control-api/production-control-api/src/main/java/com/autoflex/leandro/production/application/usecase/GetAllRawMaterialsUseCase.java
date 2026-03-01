package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRawMaterialsUseCase {

    private final RawMaterialRepository repository;

    public GetAllRawMaterialsUseCase(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public List<RawMaterial> execute() {
        return repository.findAll();
    }
}
