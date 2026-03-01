package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteRawMaterialUseCase {

    private final RawMaterialRepository repository;

    public DeleteRawMaterialUseCase(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.deleteById(id);
    }
}