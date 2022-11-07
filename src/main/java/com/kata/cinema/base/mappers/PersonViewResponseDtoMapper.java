package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonViewResponseDtoMapper {

  @Mapping( target = "fullName", expression = "java(person.getFirstName() + \" \" + person.getLastName())")
  @Mapping( target = "originalFullName", expression = "java(person.getOriginalName() + \" \" + person.getOriginalLastName())")
  @Mapping( target = "birthday", source = "dateBirth")
  @Mapping( target = "placeBirthday", source = "placeBirth")
  PersonViewResponseDto toDTO(Person person);

}
