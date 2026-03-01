package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.domain.entity.*;
import com.autoflex.leandro.production.domain.repository.ProductRawMaterialRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ListProductBillOfMaterialsUseCaseTest {

    @Mock
    private ProductRawMaterialRepository repository;

    @InjectMocks
    private ListProductBillOfMaterialsUseCase useCase;

    public ListProductBillOfMaterialsUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnBillOfMaterials() {

        Product product = new Product(1L, "P001", "Produto Teste", BigDecimal.valueOf(100.00));
        RawMaterial rm1 = new RawMaterial(2L, "RM001", "Materia 1", 100.0);
        RawMaterial rm2 = new RawMaterial(3L, "RM002", "Materia 2", 200.0);

        ProductRawMaterial association1 =
                new ProductRawMaterial(1L, product, rm1, 10.0);

        ProductRawMaterial association2 =
                new ProductRawMaterial(2L, product, rm2, 20.0);

        when(repository.findByProductId(1L))
                .thenReturn(List.of(association1, association2));

        List<ProductRawMaterial> result =
                useCase.execute(1L);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getQuantityRequired()).isEqualTo(10.0);
        assertThat(result.get(1).getQuantityRequired()).isEqualTo(20.0);
    }
}