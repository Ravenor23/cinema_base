package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.service.entity.AvailableOnlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/movies")
public class AdminOnlineSubscriptionRestController {

    private final AvailableOnlineService availableOnlineService;

    public AdminOnlineSubscriptionRestController(AvailableOnlineService availableOnlineService) {
        this.availableOnlineService = availableOnlineService;
    }

    @PostMapping("/{id}/online")
    public ResponseEntity<?> addOnline(
            @PathVariable("id") Long movieId,
            @RequestBody AvailableOnlineMovieRequestDto availableOnline) {
        availableOnlineService.save(availableOnline, movieId);
        return ResponseEntity.ok("Added online");
    }

    @PatchMapping("/{id}/online/activate")
    public ResponseEntity<?> enableOnline(@PathVariable("id") Long movieId) {
        availableOnlineService.activate(movieId);
        return ResponseEntity.ok("activated online");
    }

    @PatchMapping("/{id}/online/deactivate")
    public ResponseEntity<?> disableOnline(@PathVariable("id") Long movieId) {
        availableOnlineService.deactivate(movieId);
        return ResponseEntity.ok("deactivated online");
    }
}
