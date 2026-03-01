package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setCode(product.getCode());
        entity.setName(product.getName());
        entity.setValue(product.getValue());

        ProductJpaEntity saved = jpaProductRepository.save(entity);

        return new Product(
                saved.getId(),
                saved.getCode(),
                saved.getName(),
                saved.getValue()
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(e -> new Product(e.getId(), e.getCode(), e.getName(), e.getValue()));
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream()
                .map(e -> new Product(e.getId(), e.getCode(), e.getName(), e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaProductRepository.existsByCode(code);
    }

}