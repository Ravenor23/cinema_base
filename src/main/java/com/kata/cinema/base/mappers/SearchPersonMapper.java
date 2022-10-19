package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchPersonDto;
import com.kata.cinema.base.models.entity.Person;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SearchPersonMapper {

    SearchPersonDto toDTO(Person person);

    default List<SearchPersonDto> modelsToDTO(List<Person> people){
        return people.stream().map(this::toDTO).toList();
    }

    Person toEntity(SearchPersonDto searchPersonDto);

    @AfterMapping
    default void setFullName(@MappingTarget SearchPersonDto searchPersonDto, Person person) {
        searchPersonDto.setFullName(person.getFirstName() + " " + person.getLastName());
    }

    @AfterMapping
    default void setOriginFullName(@MappingTarget SearchPersonDto searchPersonDto, Person person) {
        searchPersonDto.setOriginalFullName(person.getOriginalName() + " " + person.getOriginalLastName());
    }
}
