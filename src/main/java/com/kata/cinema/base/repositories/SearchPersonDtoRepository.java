package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.SearchPersonDto;
import com.kata.cinema.base.models.entity.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchPersonDtoRepository extends JpaRepository<SearchPersonDto, Long> {

    List<Person> findAllByFullNameContainingIgnoreCase(String name, Pageable pageable);

    default List<Person> findTop3ByFullNameContainingIgnoreCase(String name) {
        return findAllByFullNameContainingIgnoreCase(name, PageRequest.of(0, 3));
    }
}