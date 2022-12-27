package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.mappers.ProfessionMapper;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entity.MoviePerson;
import com.kata.cinema.base.models.entity.Profession;
import com.kata.cinema.base.service.entity.MoviePersonService;
import com.kata.cinema.base.service.entity.ProfessionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminProfessionRestController {

    private final ProfessionMapper professionMapper;
    private final ProfessionService professionService;
    private final MoviePersonService moviePersonService;

    public AdminProfessionRestController(
            ProfessionMapper professionMapper, ProfessionService professionService,
            MoviePersonService moviePersonService
    ) {
        this.professionMapper = professionMapper;
        this.professionService = professionService;
        this.moviePersonService = moviePersonService;
    }

    @GetMapping("/api/professions")
    public List<ProfessionResponseDto> getProfessions() {
        List<Profession> professions = professionService.getProfessions();

        List<ProfessionResponseDto> result = new ArrayList<>();
        for (Profession profession : professions) {
            result.add(professionMapper.toDTO(profession));
        }
        return result;
    }

    @PostMapping("/api/admin/professions")
    public ProfessionResponseDto createProfession(@RequestParam("name") String name) {
        Profession profession = new Profession();
        profession.setName(name);
        professionService.save(profession);
        return professionMapper.toDTO(profession);
    }

    @PutMapping("/api/admin/professions/{id}")
    public ProfessionResponseDto createOrUpdateProfession(
            @PathVariable("id") long professionId,
            @RequestParam("name") String name
    ) {
        Profession foundProfession = professionService.getById(professionId);
        if (foundProfession != null) {
            foundProfession.setName(name);
        } else {
            foundProfession = new Profession();
            foundProfession.setId(professionId);
            foundProfession.setName(name);

        }

        professionService.save(foundProfession);
        return professionMapper.toDTO(foundProfession);
    }

    @DeleteMapping("/api/admin/professions/{id}")
    public void deleteProfession(
            @PathVariable("id") long professionId
    ) {
        List<MoviePerson> moviePersons = moviePersonService.getByProfessionId(professionId);
        if (moviePersons.isEmpty()) {
            professionService.deleteProfession(professionId);
        } else {
            throw new RuntimeException(
                    "Couldn't delete profession with id = " + professionId
            );
        }
    }
}
