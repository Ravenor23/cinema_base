package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.PersonRequestDto;
import com.kata.cinema.base.models.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonRequestDtoMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "height", source = "height")
    @Mapping(target = "dateBirth", source = "birthday")
    @Mapping(target = "placeBirth", source = "placeBirthday")
    @Mapping(target = "originalName", source = "originalName")
    @Mapping(target = "originalLastName", source = "originalLastName")
    Person toEntity(PersonRequestDto personRequestDto);

}
