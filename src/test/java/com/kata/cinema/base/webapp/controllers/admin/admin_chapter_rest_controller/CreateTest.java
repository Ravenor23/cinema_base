package com.kata.cinema.base.webapp.controllers.admin.admin_chapter_rest_controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.entity.Chapter;
import com.kata.cinema.base.service.dto.ChapterService;
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

import java.util.Objects;

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
    private MockMvc mockMvc;
    @Autowired
    private ChapterService chapterService;

    private static String accessToken;


    @Test
    @Sql(scripts = "/data/sql/controllers/adminChapterRestController/create/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminChapterRestController/create/delete.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void create() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(post("/api/admin/chapters?name=TestChapter")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
        Chapter chapter = chapterService.getByName("TestChapter");

        assert !Objects.isNull(chapter.getId());
        assert chapter.getName().equals("TestChapter");
    }
}
