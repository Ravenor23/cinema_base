package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.models.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.webapp.CinemaBaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
@SpringBootTest(classes = CinemaBaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminProductionStudioRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

    @Test
    public void saveStudioTest() throws Exception {
        ProductionStudioRequestDto productionStudioRequestDto = new ProductionStudioRequestDto(
                "Ленфильм", "Описание Ленфильма", "1914 г."
        );

        mockMvc.perform(post(createURLWithPort("/api/admin/studios"))
                        .content(objectMapper.writeValueAsString(productionStudioRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ленфильм"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Описание Ленфильма"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateFoundation").value("1914 г."));
    }

    @Test
    @Sql("data/initForDeleteTest.sql")
    public void deleteStudioTest() throws Exception {

        mockMvc.perform(delete(createURLWithPort("/api/admin/studios/100"))).andExpect(status().isNoContent());
    }

    @Test
    @Sql("data/initForUpdateTest.sql")
    public void updateStudioTest() throws Exception {
        ProductionStudioRequestDto productionStudioRequestDto = new ProductionStudioRequestDto(
                "Амедиа", "DESCRIPTION UPDATE", "2002 г."
        );

        mockMvc.perform(put(createURLWithPort("/api/admin/studios/100"))
                        .content(objectMapper.writeValueAsString(productionStudioRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Амедиа"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("DESCRIPTION UPDATE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateFoundation").value("2002 г."));
    }
}