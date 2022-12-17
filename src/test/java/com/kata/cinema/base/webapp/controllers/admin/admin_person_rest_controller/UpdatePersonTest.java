package com.kata.cinema.base.webapp.controllers.admin.admin_person_rest_controller;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.request.PersonRequestDto;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({
    TransactionalTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    SqlScriptsTestExecutionListener.class
})
public class UpdatePersonTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/update/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/update/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updatePersonTest() throws Exception {
        PersonRequestDto personRequestDto =
            new PersonRequestDto(1L,
                "David",
                "Ash",
                180.2,
                LocalDate.of(1970,
                    6,
                    23),
                "Canada",
                "David",
                "Ash");
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(put("/api/admin/persons/1")
                   .header("Authorization", "Bearer " + accessToken)
                   .content(objectMapper.writeValueAsString(personRequestDto))
                   .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("David"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ash"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.height").value(180.2))
               .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1970-06-23"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.placeBirthday").value("Canada"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.originalName").value("David"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.originalLastName").value("Ash"));
    }
}
