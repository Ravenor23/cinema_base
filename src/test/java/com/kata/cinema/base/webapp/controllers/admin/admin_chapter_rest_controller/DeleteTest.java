package com.kata.cinema.base.webapp.controllers.admin.admin_chapter_rest_controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.entity.Topic;
import com.kata.cinema.base.service.entity.TopicService;
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

import java.util.List;

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
    private MockMvc mockMvc;
    @Autowired
    private TopicService topicService;
    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/adminChapterRestController/delete/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminChapterRestController/delete/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteExistEntity() throws Exception {
        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/admin/chapters/100")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());

        List<Topic> topics = topicService.getAll()
                .stream()
                .filter(t -> t.getChapter().getId() == 100L)
                .toList();
        List<Topic> topics1 = topicService.getAll()
                .stream()
                .filter(t -> t.getChapter().getId() == 101L)
                .toList();

        assert !topics1.isEmpty();
        assert topics.isEmpty();
    }

}
