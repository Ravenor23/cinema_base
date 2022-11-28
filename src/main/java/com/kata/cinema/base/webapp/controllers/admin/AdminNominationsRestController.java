package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.entity.Nomination;
import com.kata.cinema.base.service.dto.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminNominationsRestController {

    private final NominationService nominationService;

    @Autowired
    public AdminNominationsRestController (NominationService nominationService) {
        this.nominationService = nominationService;
    }

    @GetMapping("/api/nominations")
    public List<Nomination> getNominations() {
        return nominationService.getAll();
    }

    @PostMapping("/api/admin/nominations")
    public void createNomination(@RequestParam String name) {
        Nomination nomination = new Nomination();
        nomination.setName(name);
        nominationService.createNomination(nomination);
    }

    @DeleteMapping("/api/admin/nominations/{id}")
    public void deleteNomination(@PathVariable("id") Long id) {nominationService.deleteById(id);}

    @PutMapping("/api/admin/nominations/{id}")
    public void updateNomination(@PathVariable Long id, @RequestParam String name) {
        Nomination updateNomination = nominationService.findNominationById(id);
        updateNomination.setName(name);
        nominationService.createNomination(updateNomination);
    }
}
