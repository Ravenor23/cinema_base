package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchCollectionDto;
import com.kata.cinema.base.models.entity.Collection;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = SearchCollectionMapper.class)
public interface SearchCollectionListMapper {

    List<SearchCollectionDto> toDTOList(List<Collection> collectionList);

    List<Collection> toEntityList(List<SearchCollectionDto> searchCollectionDtoList);
}
