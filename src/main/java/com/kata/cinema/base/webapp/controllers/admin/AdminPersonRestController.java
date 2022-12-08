package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.request.PersonRequestDto;
import com.kata.cinema.base.service.dto.PersonRequestDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/persons")
public class AdminPersonRestController {

    private final PersonRequestDtoService personRequestDtoService;

    public AdminPersonRestController(PersonRequestDtoService personRequestDtoService) {
        this.personRequestDtoService = personRequestDtoService;
    }

    @PostMapping
    public ResponseEntity<PersonRequestDto> addPerson(@RequestBody PersonRequestDto personRequestDto) {
        personRequestDtoService.save(personRequestDto);
        return new ResponseEntity<>(personRequestDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonRequestDto> updatePerson(@RequestBody PersonRequestDto personRequestDto, @PathVariable Long id) {
        personRequestDtoService.update(id, personRequestDto);
        return new ResponseEntity<>(personRequestDto, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personRequestDtoService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
