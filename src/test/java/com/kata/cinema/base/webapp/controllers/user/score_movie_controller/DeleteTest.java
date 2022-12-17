package com.kata.cinema.base.webapp.controllers.user.score_movie_controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.exceptions.NoScoreException;
import com.kata.cinema.base.exceptions.WrongUserScoreException;
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

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class DeleteTest {

    @Autowired
    private ScoreMovieService scoreService;

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/simpleDelete/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/simpleDelete/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/user/scores/100")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());

        assert scoreService.findOptionalById(100L).isEmpty();
        assert scoreService.findOptionalById(101L).isPresent();
    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/deleteNonExistent/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/deleteNonExistent/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteNonExistent() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/user/scores/200")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is(400))
                .andExpect(e -> e.getResolvedException().getClass().equals(NoScoreException.class));
    }

    @Test
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/simpleDelete/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/scoreMovieController/delete/simpleDelete/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteOtherUserScore() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/user/scores/103")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is(400))
                .andExpect(e -> e.getResolvedException().getClass().equals(WrongUserScoreException.class));
    }
}
