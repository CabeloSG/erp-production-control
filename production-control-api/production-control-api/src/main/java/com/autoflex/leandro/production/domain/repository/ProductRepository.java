package com.autoflex.leandro.production.domain.repository;

import com.autoflex.leandro.production.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    boolean existsByCode(String code);

}