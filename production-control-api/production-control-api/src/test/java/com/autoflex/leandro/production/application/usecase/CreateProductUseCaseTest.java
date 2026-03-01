package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private CreateProductUseCase useCase;

    @Test
    void shouldCreateProductSuccessfully() {

        ProductRequestDTO request =
                new ProductRequestDTO("P001", "Chair", BigDecimal.valueOf(150));

        Product savedProduct =
                new Product(1L, "P001", "Chair", BigDecimal.valueOf(150));

        when(repository.save(any())).thenReturn(savedProduct);

        var response = useCase.execute(request);

        assertNotNull(response);
        assertEquals("P001", response.getCode());
        assertEquals("Chair", response.getName());
        assertEquals(BigDecimal.valueOf(150), response.getValue());

        verify(repository, times(1)).save(any());
    }
}