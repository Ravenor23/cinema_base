package com.kata.cinema.base.webapp.controllers.admin.admin_person_rest_controller;

import static com.kata.cinema.base.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
public class DeletePersonTest {

    @Autowired
    private MockMvc mockMvc;

    private static String accessToken;

    @Test
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/delete/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/delete/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deletePersonTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/admin/persons/1")
                   .header("Authorization", "Bearer " + accessToken))
               .andExpect(MockMvcResultMatchers.content().string("deleted"));
    }

    @Test
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/delete/init.sql",
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/adminPersonRestController/delete/delete.sql",
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void negativeDeletePersonTest() throws Exception {

        accessToken = obtainNewAccessToken("user1@mail.ru", "101", mockMvc);

        mockMvc.perform(delete("/api/admin/persons/2")
                   .header("Authorization", "Bearer " + accessToken))
               .andExpect(status().is(500));
    }
}
