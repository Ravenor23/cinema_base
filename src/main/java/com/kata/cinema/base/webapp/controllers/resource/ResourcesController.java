package com.kata.cinema.base.webapp.controllers.resource;

import com.kata.cinema.base.service.recource.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class ResourcesController {

    private final ImageService imageService;

    @Autowired
    public ResourcesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/uploads/{pngFile}")
    public ResponseEntity<byte[]> getImage(@PathVariable String pngFile) throws IOException {
        return imageService.pngToResponse("src/main/resources/uploads/movies/preview/" + pngFile);
    }
}