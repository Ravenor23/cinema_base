package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchCollectionDto;
import com.kata.cinema.base.models.entity.Collection;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface SearchCollectionMapper {

    SearchCollectionDto toDTO(Collection collection);

    Collection toEntity(SearchCollectionDto searchCollectionDto);
}
