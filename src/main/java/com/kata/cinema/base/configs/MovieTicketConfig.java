package com.kata.cinema.base.configs;

import com.kata.cinema.base.service.entity.MovieTicketService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(value = "com.kata.cinema.base")
public class MovieTicketConfig {

    private final MovieTicketService movieTicketService;

    public MovieTicketConfig(MovieTicketService movieTicketService) {
        this.movieTicketService = movieTicketService;
    }

    @Async
    @Scheduled(cron = "0 0 6 * * ?")
    public void save() {
        movieTicketService.save();
    }

    @Async
    @Scheduled(cron = "0 0 6 * * ?")
    public void delete() {
        movieTicketService.delete();
    }
}
