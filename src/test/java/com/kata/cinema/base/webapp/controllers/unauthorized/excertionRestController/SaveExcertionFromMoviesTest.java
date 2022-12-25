package com.kata.cinema.base.webapp.controllers.unauthorized.excertionRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.entity.Excertion;
import org.assertj.core.api.Assertions;
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

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class SaveExcertionFromMoviesTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Sql(scripts = "/data/sql/controllers/excertionRestController/saveFromMovies/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/excertionRestController/saveFromMovies/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void saveFromMoviesTest() throws Exception {
        ExcertionRequestDto excertionRequestDto = new ExcertionRequestDto("excertion description");

        mockMvc.perform(
                post("/api/movies/{id}/excertions", 101)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(excertionRequestDto)))
                .andExpect(status().isOk());

        Excertion excertion = (Excertion) entityManager.createQuery("select e from Excertion e where e.id=1").getSingleResult();
        Assertions.assertThat(excertion.getId()).isEqualTo(1L);
        Assertions.assertThat(excertion.getDescription()).isEqualTo("excertion description");
        Assertions.assertThat(excertion.getMovie().getId()).isEqualTo(101L);
    }
}
