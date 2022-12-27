package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.GenreMapper;
import com.kata.cinema.base.mappers.PersonViewResponseDtoMapper;
import com.kata.cinema.base.mappers.ProfessionMapper;
import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.repositories.GenreRepository;
import com.kata.cinema.base.repositories.PersonRepository;
import com.kata.cinema.base.repositories.ProfessionRepository;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonViewResponseDtoServiceImpl implements PersonViewResponseDtoService {

  private final PersonRepository personRepositories;
  private final PersonViewResponseDtoMapper personViewResponseDtoMapper;
  private final GenreRepository genreRepository;
  private final GenreMapper genreMapper;
  private final ProfessionRepository professionRepositories;
  private final ProfessionMapper professionMapper;

  public PersonViewResponseDtoServiceImpl(PersonRepository personRepositories,
                                          PersonViewResponseDtoMapper personViewResponseDtoMapper, GenreRepository genreRepository,
                                          GenreMapper genreMapper, ProfessionRepository professionRepositories,
                                          ProfessionMapper professionMapper) {
    this.personRepositories = personRepositories;
    this.personViewResponseDtoMapper = personViewResponseDtoMapper;
    this.genreRepository = genreRepository;
    this.genreMapper = genreMapper;
    this.professionRepositories = professionRepositories;
    this.professionMapper = professionMapper;
  }

  private List<GenreResponseDto> getGenresResponseDto(Long id) {
    return genreMapper.modelsToDTO(genreRepository.getGenresByPerson(id).stream().toList());
  }

  List<ProfessionResponseDto> getProfessionResponseDto(Long id) {
    return professionMapper.modelsToDTO(professionRepositories.getProfessionsByPerson(id).stream().toList());
  }

  @Override
  public PersonViewResponseDto findPersonById(Long id) {
    PersonViewResponseDto personViewResponseDto = personViewResponseDtoMapper.toDTO(personRepositories.getPersonById(id));
    personViewResponseDto.setGenres(getGenresResponseDto(id));
    personViewResponseDto.setProfession(getProfessionResponseDto(id));
    return personViewResponseDto;
  }
}
