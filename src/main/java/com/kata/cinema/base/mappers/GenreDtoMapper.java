package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.GenreMovieDto;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Movie;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GenreDtoMapper {

    @Mapping(target = "movieId", source = "id")
    @Mapping(target = "genres", source = "genres", qualifiedByName = "mapGenres")
    GenreMovieDto toDto(Movie movie);

    @Named("mapGenres")
    default String mapGenresToString(Set<Genre> genres) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Genre> genresSortedList = new ArrayList<>(genres);
        genresSortedList.sort(
            Comparator.comparing(Genre::getId)
        );
        for (Genre genre : genresSortedList) {
            stringBuilder.append(genre.getName()).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
