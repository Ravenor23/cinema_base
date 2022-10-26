package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.*;
import com.kata.cinema.base.models.dto.response.SearchCollectionDto;
import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.dto.response.SearchPersonDto;
import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import com.kata.cinema.base.repositories.SearchCollectionDtoRepository;
import com.kata.cinema.base.repositories.SearchMovieDtoRepository;
import com.kata.cinema.base.repositories.SearchPersonDtoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchResponseService {

    private final SearchMovieDtoRepository searchMovieDtoRepository;
    private final SearchMovieListMapper searchMovieListMapper;
    private final SearchCollectionDtoRepository searchCollectionDtoRepository;
    private final SearchCollectionListMapper searchCollectionListMapper;
    private final SearchPersonDtoRepository searchPersonDtoRepository;
    private final SearchPersonListMapper searchPersonListMapper;

    public SearchResponseService(SearchMovieDtoRepository searchMovieDtoRepository, SearchMovieListMapper searchMovieListMapper, SearchCollectionDtoRepository searchCollectionDtoRepository, SearchCollectionListMapper searchCollectionListMapper, SearchPersonDtoRepository searchPersonDtoRepository, SearchPersonListMapper searchPersonListMapper) {
        this.searchMovieDtoRepository = searchMovieDtoRepository;
        this.searchMovieListMapper = searchMovieListMapper;
        this.searchCollectionDtoRepository = searchCollectionDtoRepository;
        this.searchCollectionListMapper = searchCollectionListMapper;
        this.searchPersonDtoRepository = searchPersonDtoRepository;
        this.searchPersonListMapper = searchPersonListMapper;
    }


    public List<SearchMovieDto> searchMovieName(String name) {
        return searchMovieListMapper.toDTOList(searchMovieDtoRepository.findTop3byNameContainingIgnoreCase(name));
    }

    public List<SearchCollectionDto> searchCollectionName(String name) {
        return searchCollectionListMapper.toDTOList(searchCollectionDtoRepository.findTop3ByNameContainingIgnoreCase(name));
    }

    public List<SearchPersonDto> searchPersonFullName(String fullName) {
        return searchPersonListMapper.toDTOList(searchPersonDtoRepository.findTop3ByFullNameContainingIgnoreCase(fullName));
    }

    public SearchResponseDto searchResponseDtoList(String name) {

        List<SearchMovieDto> searchMovieDtoList = new ArrayList<>(searchMovieName(name));

        List<SearchCollectionDto> searchCollectionDto = new ArrayList<>(searchCollectionName(name));

        List<SearchPersonDto> searchPersonDto = new ArrayList<>(searchPersonFullName(name));

        return new SearchResponseDto(searchMovieDtoList, searchCollectionDto, searchPersonDto);
    }
}
