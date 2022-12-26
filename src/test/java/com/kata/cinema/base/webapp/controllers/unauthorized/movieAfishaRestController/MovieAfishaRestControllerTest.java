package com.kata.cinema.base.webapp.controllers.unauthorized.movieAfishaRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import java.time.LocalDate;
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
public class MovieAfishaRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "/data/sql/controllers/movieAfishaRestController/getMoviesAfisha/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/movieAfishaRestController/getMoviesAfisha/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getMoviesAfisha() throws Exception {

        MovieResponseDto movieResponseDto =
            new MovieResponseDto(100L, "фильм1", "фильм1", 3, LocalDate.of(2022, 12, 25), "Russia", "жанр1, жанр2",
                "режисер1", "роль1", 1L);
        MovieResponseDto movieResponseDto2 =
            new MovieResponseDto(101L, "фильм2", "фильм2", 3, LocalDate.of(2022, 12, 29), "USA", "жанр1, жанр3",
                "режисер2", "роль2", 1L);
        MovieResponseDto movieResponseDto3 =
            new MovieResponseDto(102L, "фильм3", "фильм3", 2, LocalDate.of(2022, 12, 23), "Canada",
                "жанр1, жанр5, жанр8",
                "режисер3", "роль3", 1L);
        PageDto<MovieResponseDto> pageDto = new PageDto<>(3L, List.of(movieResponseDto, movieResponseDto2, movieResponseDto3));

        mockMvc.perform(get("/api/afisha?genreId=100"))
               .andExpect(status().isOk())
               .andExpect(content().json(
                   objectMapper.writeValueAsString(pageDto)
                   ));
    }
}
