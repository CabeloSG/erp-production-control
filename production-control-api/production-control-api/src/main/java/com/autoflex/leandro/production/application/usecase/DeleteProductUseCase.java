package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.repository.ProductRepository;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        productRepository.deleteById(id);
    }
}
