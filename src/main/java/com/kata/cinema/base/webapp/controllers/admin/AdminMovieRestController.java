package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.impl.ContentServiceImp;
import java.io.File;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
public class AdminMovieRestController {
    private final ContentServiceImp contentService;

    private final MovieService movieService;

    public AdminMovieRestController(ContentServiceImp contentService, MovieService movieService) {
        this.contentService = contentService;
        this.movieService = movieService;
    }

    @PostMapping("/{id}/uploadPreview")
    public ResponseEntity<?> uploadPreview(@PathVariable("id") Long movieId,
                                                @RequestParam("image") MultipartFile file) throws IOException {

        File convFile = new File("/uploads/movies/preview");
        file.transferTo(convFile);
        contentService.saveFile(movieId, convFile);
        return ResponseEntity.ok("upload");
    }

    @PostMapping()
    public ResponseEntity<?>  addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.save(movieRequestDto);
        return ResponseEntity.ok("save");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMovie(@RequestBody MovieRequestDto movieRequestDto,
        @PathVariable Long id) {
        movieService.updateMovie(id, movieRequestDto);
        return ResponseEntity.ok("edit");
    }

}