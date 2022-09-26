package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.service.dto.impl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminGenreRestController {

    private final GenreService genreService;

    @Autowired
    public AdminGenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/api/admin/genres")
    public List<GenreResponseDto> getAllGenre() {
        return genreService.getAllGenre();
    }

    @PostMapping("/api/admin/genres")
    public String createGenre(@RequestBody GenreResponseDto genreResponseDto) {
        genreService.createGenre(genreResponseDto);
        return "redirect:/genres";
    }

    @PutMapping("/api/admin/genres/{id}")
    public String updateGenre(@PathVariable long id,@RequestBody GenreResponseDto genreResponseDto) {
        Genre updateGenre = genreService.findGenreById(id);
        updateGenre.setName(genreResponseDto.getName());
        genreService.createGenre(genreResponseDto);
        return "redirect:/genres";
    }

    @DeleteMapping("/api/admin/genres/{id}")
    public String delete(@PathVariable("id") Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }
}
