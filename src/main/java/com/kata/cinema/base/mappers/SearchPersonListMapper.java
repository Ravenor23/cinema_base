package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchPersonDto;
import com.kata.cinema.base.models.entity.Person;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",uses = SearchPersonMapper.class)
public interface SearchPersonListMapper {

    List<SearchPersonDto> toDTOList(List<Person> personList);

    List<Person> toEntityList(List<SearchPersonDto> searchPersonDtoList);
}
