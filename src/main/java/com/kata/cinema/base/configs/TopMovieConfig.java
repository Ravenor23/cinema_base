package com.kata.cinema.base.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(value = "com.kata.cinema.base")
public class TopMovieConfig {

    private final JdbcTemplate jdbcTemplate;

    public TopMovieConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async
    @Scheduled(cron = "0 0 0 * * SUN")
    public void set() throws IOException {

        jdbcTemplate.update(Files.readString(Paths.get("src/main/resources/static/data/top_movies_set.sql")));
    }
}