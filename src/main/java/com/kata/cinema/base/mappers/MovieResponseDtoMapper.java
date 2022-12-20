package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Movie;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovieResponseDtoMapper {

    @Mapping(target = "genres", source = "genres", qualifiedByName = "mapGenres")
    @Mapping(target = "dateRelease", source = "dataRelease")
    @Mapping(target = "originalName", source = "originName")
    MovieResponseDto toDto(Movie movie);

    @Named("mapGenres")
    default String mapGenresToString(Set<Genre> genres) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre : genres) {
            stringBuilder.append(genre.getName()).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
