package com.kata.cinema.base.webapp.controllers.unauthorized.excertionRestController;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kata.cinema.base.models.entity.Excertion;
import org.assertj.core.api.Assertions;
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

import javax.persistence.EntityManager;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class GetExcertionFromPersonTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Sql(scripts = "/data/sql/controllers/excertionRestController/getFromPerson/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/sql/controllers/excertionRestController/getFromPerson/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getFromPersonTest() throws Exception {
        long counter = 101L;
        mockMvc.perform(get("/api/person/{id}/excertions/page/1?itemsOnPage=5", 101))
                .andExpect(status().isOk());

        List<Excertion> excertionList = entityManager.createQuery("select e from Excertion e where e.person.id=101").getResultList();

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(excertionList.get(i).getId()).isEqualTo(counter++);
            Assertions.assertThat(excertionList.get(i).getDescription()).isEqualTo("excertion description");
            Assertions.assertThat(excertionList.get(i).getPerson().getId()).isEqualTo(101L);
        }

    }
}
