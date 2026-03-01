package com.autoflex.leandro.production.application.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductBillOfMaterialsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAssociateRawMaterialToProduct() throws Exception {

        // cria produto
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"P900",
              "name":"Produto Teste",
              "value":10
            }
            """));

        // cria matéria prima
        mockMvc.perform(post("/raw-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"RM900",
              "name":"Aço",
              "quantityInStock":100
            }
            """));

        // associa (RF003)
        mockMvc.perform(post("/products/1/bill-of-materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "rawMaterialId":1,
              "quantity":10
            }
            """))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCalculateProductionCapacity() throws Exception {

        // produto
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"P300",
              "name":"Produto Capacidade",
              "value":20
            }
            """));

        // matéria prima
        mockMvc.perform(post("/raw-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"RM300",
              "name":"Ferro",
              "quantityInStock":100
            }
            """));

        // associação BOM
        mockMvc.perform(post("/products/1/bill-of-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "rawMaterialId":1,
              "quantity":10
            }
            """));

        // RF004
        mockMvc.perform(get("/products/1/bill-of-materials/production-capacity"))
                .andExpect(status().isOk());
    }

}