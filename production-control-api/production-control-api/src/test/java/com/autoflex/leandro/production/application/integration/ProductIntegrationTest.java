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
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProduct() throws Exception {

        String json = """
        {
          "code":"P100",
          "name":"Produto Teste",
          "value":10.50
        }
        """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllProducts() throws Exception {

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        var result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
        {
          "code":"P400",
          "name":"Produto Delete",
          "value":10
        }
        """))
                .andReturn();

        String response = result.getResponse().getContentAsString();

        // pega ID do JSON
        Long id = Long.valueOf(
                response.split("\"id\":")[1].split(",")[0].trim()
        );

        mockMvc.perform(delete("/products/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotDeleteProductWithBillOfMaterials() throws Exception {

        // cria produto
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"P300",
              "name":"Produto Com BOM",
              "value":10
            }
            """));

        // cria matéria-prima
        mockMvc.perform(post("/raw-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "code":"RM300",
              "name":"Aço",
              "quantityInStock":100
            }
            """));

        // associa BOM
        mockMvc.perform(post("/products/1/bill-of-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
              "rawMaterialId":1,
              "quantity":10
            }
            """));

        // tenta deletar
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isConflict());
    }

}