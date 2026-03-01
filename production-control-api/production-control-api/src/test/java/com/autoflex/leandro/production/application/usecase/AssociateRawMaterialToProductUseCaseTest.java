package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.*;
import com.autoflex.leandro.production.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AssociateRawMaterialToProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @Mock
    private ProductRawMaterialRepository productRawMaterialRepository;

    @InjectMocks
    private AssociateRawMaterialToProductUseCase useCase;

    public AssociateRawMaterialToProductUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAssociateRawMaterialToProduct() {

        Product product = new Product(1L, "P001", "Produto Teste", BigDecimal.valueOf(100.00));
        RawMaterial rawMaterial = new RawMaterial(2L, "RM001", "Materia Prima", 50.0);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(rawMaterialRepository.findById(2L))
                .thenReturn(Optional.of(rawMaterial));

        when(productRawMaterialRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductRawMaterial result =
                useCase.execute(1L, 2L, 5.0);

        assertThat(result).isNotNull();
        assertThat(result.getProduct().getId()).isEqualTo(1L);
        assertThat(result.getRawMaterial().getId()).isEqualTo(2L);
        assertThat(result.getQuantityRequired()).isEqualTo(5.0);

        verify(productRawMaterialRepository).save(any());
    }
}