package com.kata.cinema.base.webapp.controllers.user.score_movie_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class GetAllTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    ScoreMovieResponseDto responseDto1 = new ScoreMovieResponseDto(100L,
            1,
            LocalDate.of(2022, Month.JANUARY, 2),
            123, 200L,
            "Ох уж эти орки!",
            "Orcs! Orcs! Orcs!",
            LocalDate.of(2021, Month.JANUARY, 12),
            2.5,
            2L);
    ScoreMovieResponseDto responseDto2 = new ScoreMovieResponseDto(102L,
            9,
            LocalDate.of(2022, Month.JANUARY, 7),
            3425, 201L,
            "Тор возвращается",
            "Thor comes back",
            LocalDate.of(2020, Month.APRIL, 12),
            6.67,
            3L);
    ScoreMovieResponseDto responseDto3 = new ScoreMovieResponseDto(105L,
            1,
            LocalDate.of(2022, Month.JULY, 1),
            777, 202L,
            "Веселье в Кентукки",
            "Happy Kentucky",
            LocalDate.of(2001, Month.MAY, 12),
            1.0,
            1L);

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTest() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        PageDto<ScoreMovieResponseDto> testDto = new PageDto<>(6L, List.of(responseDto1, responseDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/scores/page/1?itemsOnPage=2")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testDto)));

    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllScoreSortTest() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        PageDto<ScoreMovieResponseDto> testDto = new PageDto<>(6L, List.of(responseDto3, responseDto1, responseDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/scores/page/1?itemsOnPage=3&sortScoreType=SCORE_ASC")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testDto)));

    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllNameSortTest() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        PageDto<ScoreMovieResponseDto> testDto = new PageDto<>(6L, List.of(responseDto3, responseDto1, responseDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/scores/page/1?itemsOnPage=3&sortScoreType=NAME_ASC")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testDto)));

    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/get/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllDateReleaseSortTest() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        PageDto<ScoreMovieResponseDto> testDto = new PageDto<>(6L, List.of(responseDto3, responseDto2, responseDto1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/scores/page/1?itemsOnPage=3&sortScoreType=DATE_RELEASE_ASC")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testDto)));

    }


}
