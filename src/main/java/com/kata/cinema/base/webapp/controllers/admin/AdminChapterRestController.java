package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.exceptions.NoChapterException;
import com.kata.cinema.base.models.dto.response.ChapterResponseDto;
import com.kata.cinema.base.service.dto.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/chapters")
public class AdminChapterRestController {

    private final ChapterService chapterService;

    public AdminChapterRestController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping()
    public ResponseEntity<?> createChapter(
            @RequestParam(name = "name") String chapterName) {

        chapterService.createChapter(chapterName);

        return ResponseEntity.ok(new StringBuilder()
                .append("Chapter created. ")
                .append("Name = ")
                .append(chapterName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapter(@PathVariable(name = "id") Long id) {

        chapterService.getOptionalById(id).orElseThrow(() -> new NoChapterException(id));

        chapterService.deleteChapter(id);
        return ResponseEntity.ok(new StringBuilder()
                .append("Chapter deleted. Id = ")
                .append(id.toString()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChapter(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "name") String newName) {
        chapterService.updateChapter(id, newName);
        return ResponseEntity.ok(new StringBuilder().append("Chapter updated. New name: ")
                .append(newName));
    }

    @GetMapping
    public List<ChapterResponseDto> getAllChapters() {
        return chapterService.getAllChapters();
    }
}
