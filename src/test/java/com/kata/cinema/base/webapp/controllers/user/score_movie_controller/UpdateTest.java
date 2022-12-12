package com.kata.cinema.base.webapp.controllers.user.score_movie_controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.exceptions.NoScoreException;
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
import java.time.Month;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
    private ScoreMovieService scoreService;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/update/simpleUpdate/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/update/simpleUpdate/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updateTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(patch("/api/user/movies/200/score?score=9")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
        Score enteredUserScore = scoreService.findScoreMovieById(100L);
        Score otherUserScore = scoreService.findScoreMovieById(101L);

        assert enteredUserScore.getScore() == 9;
        assert enteredUserScore.getDate().equals(LocalDate.now());
        assert otherUserScore.getScore() == 4;
        assert otherUserScore.getDate().equals(LocalDate.of(2022, Month.JANUARY, 5));
    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/update/updateNonExistent/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/update/updateNonExistent/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updateNonExistentScore() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(patch("/api/user/movies/200/score?score=9")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is(400))
                .andExpect(e -> e.getResolvedException().getClass().equals(NoScoreException.class));
    }
}
