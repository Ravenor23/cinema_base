package com.kata.cinema.base.webapp.controllers.unauthorized.contentRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.response.ContentResponseDto;
import com.kata.cinema.base.models.enums.TypeContent;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({
    TransactionalTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    SqlScriptsTestExecutionListener.class
})
public class GetMoviesContentTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "/data/sql/controllers/contentRestController/getMoviesContent/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/contentRestController/getMoviesContent/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getMoviesContentTest() throws Exception {

        ContentResponseDto contentResponseDto1 =
            new ContentResponseDto(1L, "https://www.example.ru", TypeContent.MOVIES);
        ContentResponseDto contentResponseDto2 =
            new ContentResponseDto(2L, "https://www.example2.ru", TypeContent.PREVIEW);
        ContentResponseDto contentResponseDto3 =
            new ContentResponseDto(3L, "https://www.example3.ru", TypeContent.TRAILER);

        mockMvc.perform(get("/api/movies/1/content"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(
                   List.of(contentResponseDto1, contentResponseDto2, contentResponseDto3)
               )));
    }

    @Test
    @Sql(scripts = "/data/sql/controllers/contentRestController/getMoviesContent/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/contentRestController/getMoviesContent/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void negativeGetMoviesContentTest() throws Exception {

        mockMvc.perform(get("/api/movies/3/content"))
               .andExpect(status().is(500));
    }
}
