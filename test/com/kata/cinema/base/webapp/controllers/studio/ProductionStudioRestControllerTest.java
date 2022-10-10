package com.kata.cinema.base.webapp.controllers.studio;

import com.kata.cinema.base.webapp.CinemaBaseApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = CinemaBaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductionStudioRestControllerTest {

    static {
        // Set environment variables for tests

        System.setProperty("POSTGRESQL_SERVER", "localhost");
        System.setProperty("POSTGRESQL_PORT", "5432");
        System.setProperty("POSTGRESQL_DB", "postgres");
        System.setProperty("POSTGRESQL_USERNAME", "");
        System.setProperty("POSTGRESQL_PASSWORD", "");
        System.setProperty("HIBERNATE_DDL", "create");
    }

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

    @Test
    @Sql("data/initForGetByIdTest.sql")
    public void getProductionMovieStudioByIdTest() throws JSONException {

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/api/movies/19/studios"),
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        String expectedResponseBody = "{\"name\":\"Аватар\",\"studioPerformance\":{\"id\":6,\"name\":\"PERFORM_NAME\"}}";

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), false);
    }
}