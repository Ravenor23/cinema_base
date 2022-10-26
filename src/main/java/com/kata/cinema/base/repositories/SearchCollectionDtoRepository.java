package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.SearchCollectionDto;
import com.kata.cinema.base.models.entity.Collection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchCollectionDtoRepository extends JpaRepository<SearchCollectionDto, Long> {

    List<Collection> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    default List<Collection> findTop3ByNameContainingIgnoreCase(String name) {
        return findAllByNameContainingIgnoreCase(name, PageRequest.of(0, 3));
    }
}