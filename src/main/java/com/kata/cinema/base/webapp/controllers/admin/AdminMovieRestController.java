package com.kata.cinema.base.webapp.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/moderator/movie")
public class AdminMovieRestController {

    @PostMapping("/{id}/uploadPreview")
    public ResponseEntity<String> uploadPreview(@PathVariable("id") String movieId,
                                                @RequestParam("image") MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("/uploads/movies/preview") + "/" + movieId);
        file.transferTo(convFile);
        return ResponseEntity.ok("upload");
    }
}