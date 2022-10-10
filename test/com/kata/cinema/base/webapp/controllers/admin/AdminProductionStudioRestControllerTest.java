package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.models.dto.response.ProductionStudioResponseDto;
import com.kata.cinema.base.webapp.CinemaBaseApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = CinemaBaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminProductionStudioRestControllerTest {

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
    public void saveStudioTest() {
        ProductionStudioResponseDto testStudioResponse = new ProductionStudioResponseDto(
                null, "Ленфильм", "Описание Ленфильма", "1914 г."
        );

        ResponseEntity<ProductionStudioResponseDto> responseEntity = restTemplate.postForEntity(
                createURLWithPort("/api/admin/studios"),
                testStudioResponse,
                ProductionStudioResponseDto.class
        );

        Assertions.assertEquals(201, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(responseEntity.getBody(), testStudioResponse);
    }

    @Test
    @Sql("data/initForDeleteTest.sql")
    public void deleteStudioTest() throws JSONException {

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/api/admin/studios/1"),
                HttpMethod.DELETE,
                httpEntity,
                String.class
        );

        Assertions.assertEquals(204, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(null, responseEntity.getBody(), false);
    }

    @Test
    @Sql("data/initForUpdateTest.sql")
    public void updateStudioTest() throws JsonProcessingException, JSONException {

        ProductionStudioResponseDto updateStudioResponseDto = new ProductionStudioResponseDto(
                (long)2, "Амедиа", "DESCRIPTION UPDATE", "2002 г."
        );

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(updateStudioResponseDto);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/api/admin/studios/2"),
                HttpMethod.PUT,
                httpEntity,
                String.class
        );

        String expectedResponseBody =
                "{\"name\":\"Амедиа\",\"description\":\"DESCRIPTION UPDATE\",\"dateFoundation\":\"2002 г.\"}";

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), false);
    }
}