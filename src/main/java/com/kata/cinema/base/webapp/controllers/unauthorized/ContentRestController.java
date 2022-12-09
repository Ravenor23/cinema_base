package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.ContentResponseDto;
import com.kata.cinema.base.service.entity.ContentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContentRestController {

    private final ContentService contentService;

    public ContentRestController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/movies/{id}/content")
    public ResponseEntity<List<ContentResponseDto>> getMoviesContent(@PathVariable Long id) {
        List<ContentResponseDto> contentByMovieId = contentService.getContentByMovieId(id);
        return new ResponseEntity<>(contentByMovieId, HttpStatus.OK);
    }

    @GetMapping("/person/{id}/content")
    public ResponseEntity<List<ContentResponseDto>> getPersonContent(@PathVariable Long id) {
        List<ContentResponseDto> contentByMovieId = contentService.getContentByPersonId(id);
        return new ResponseEntity<>(contentByMovieId, HttpStatus.OK);
    }
}
