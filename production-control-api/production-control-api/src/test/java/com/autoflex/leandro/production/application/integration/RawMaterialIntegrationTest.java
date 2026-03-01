package com.autoflex.leandro.production.application.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class RawMaterialIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldCreateRawMaterial() throws Exception {

        String json = """
        {
          "code":"RM100",
          "name":"Aço",
          "quantityInStock":100
        }
        """;

        mockMvc.perform(post("/raw-materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("RM100"));
    }

    @Test
    void shouldUpdateRawMaterial() throws Exception {

        String create = """
        {
          "code":"RM200",
          "name":"Ferro",
          "quantityInStock":50
        }
        """;

        mockMvc.perform(post("/raw-materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(create));

        String update = """
        {
          "code":"RM201",
          "name":"Ferro Atualizado",
          "quantityInStock":80
        }
        """;

        mockMvc.perform(put("/raw-materials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(update))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("RM201"));
    }

    @Test
    void shouldGetRawMaterialById() throws Exception {

        mockMvc.perform(get("/raw-materials/1"))
                .andExpect(status().isOk());
    }
}