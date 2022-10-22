package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.SearchCollectionMapper;
import com.kata.cinema.base.mappers.SearchMovieMapper;
import com.kata.cinema.base.mappers.SearchPersonMapper;
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
    private final SearchMovieMapper searchMovieMapper;
    private final SearchCollectionDtoRepository searchCollectionDtoRepository;
    private final SearchCollectionMapper searchCollectionMapper;
    private final SearchPersonDtoRepository searchPersonDtoRepository;
    private final SearchPersonMapper searchPersonMapper;

    public SearchResponseService(SearchMovieDtoRepository searchMovieDtoRepository, SearchMovieMapper searchMovieMapper, SearchCollectionDtoRepository searchCollectionDtoRepository, SearchCollectionMapper searchCollectionMapper, SearchPersonDtoRepository searchPersonDtoRepository, SearchPersonMapper searchPersonMapper) {
        this.searchMovieDtoRepository = searchMovieDtoRepository;
        this.searchMovieMapper = searchMovieMapper;
        this.searchCollectionDtoRepository = searchCollectionDtoRepository;
        this.searchCollectionMapper = searchCollectionMapper;
        this.searchPersonDtoRepository = searchPersonDtoRepository;
        this.searchPersonMapper = searchPersonMapper;
    }

    public List<SearchMovieDto> searchMovieName(String name) {
        return searchMovieMapper.modelsToDTO(searchMovieDtoRepository.findAllByNameContainingIgnoreCase(name));
    }

    public List<SearchMovieDto> searchMovieOriginalName(String originalName) {
        return searchMovieMapper.modelsToDTO(searchMovieDtoRepository.findAllByOriginalNameContainingIgnoreCase(originalName));
    }

    public List<SearchCollectionDto> searchCollectionName(String name) {
        return searchCollectionMapper.modelsToDTO(searchCollectionDtoRepository.findAllByNameContainingIgnoreCase(name));
    }

    public List<SearchPersonDto> searchPersonFullName(String fullName) {
        return searchPersonMapper.modelsToDTO(searchPersonDtoRepository.findAllByFullNameContainingIgnoreCase(fullName));
    }

    public List<SearchPersonDto> searchPersonOriginalFullName(String originalName) {
        return searchPersonMapper.modelsToDTO(searchPersonDtoRepository.findAllByOriginalFullNameContainingIgnoreCase(originalName));
    }

    public SearchResponseDto searchResponseDtoList(String name) {
        List<SearchMovieDto> searchMovieDtoList = new ArrayList<>(searchMovieName(name).stream().limit(3).toList());
        searchMovieDtoList.addAll(searchMovieOriginalName(name).stream().limit(3).toList());
        searchMovieDtoList.stream().limit(3);

        List<SearchCollectionDto> searchCollectionDto = new ArrayList<>(searchCollectionName(name).stream().limit(3).toList());

        List<SearchPersonDto> searchPersonDto = new ArrayList<>(searchPersonFullName(name).stream().limit(3).toList());
        searchPersonDto.addAll(searchPersonOriginalFullName(name).stream().limit(3).toList());
        searchPersonDto.stream().limit(3);
        return new SearchResponseDto(searchMovieDtoList,searchCollectionDto,searchPersonDto);
    }
}
