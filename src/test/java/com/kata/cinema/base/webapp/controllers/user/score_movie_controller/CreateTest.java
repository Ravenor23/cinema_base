package com.kata.cinema.base.webapp.controllers.user.score_movie_controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.exceptions.ScoreAlreadyExistsException;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.service.dto.ScoreMovieService;
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

import java.time.LocalDate;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
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
public class CreateTest {

    @Autowired
    private ScoreMovieService scoreService;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/create/createFirst/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/create/createFirst/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void createTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(post("/api/user/movies/200/score?score=5")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
        Score score = scoreService.getAll().stream().filter(s -> s.getUser().getId() == 100L).findAny().get();

        assert score.getScore() == 5;
        assert score.getMovie().getId() == 200L;
        assert score.getDate().equals(LocalDate.now());

    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/create/createSecond/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/create/createSecond/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void createSecondScoreTest() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(post("/api/user/movies/200/score?score=5")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is(400))
                .andExpect(e -> e.getResolvedException().getClass().equals(ScoreAlreadyExistsException.class));
    }
}
