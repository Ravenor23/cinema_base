package com.kata.cinema.base.webapp.controllers.admin.admin_production_studio_rest_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.webapp.CinemaBaseApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class
})
public class UpdateTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/adminProductionStudioRestController/update/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminProductionStudioRestController/update/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updateStudioTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        ProductionStudioRequestDto productionStudioRequestDto = new ProductionStudioRequestDto(
                "Амедиа", "DESCRIPTION UPDATE", "2002 г."
        );

        mockMvc.perform(put("/api/admin/studios/100")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(productionStudioRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Амедиа"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("DESCRIPTION UPDATE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateFoundation").value("2002 г."));
    }
}