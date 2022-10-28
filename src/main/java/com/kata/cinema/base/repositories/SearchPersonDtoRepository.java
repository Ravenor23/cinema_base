package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Person;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchPersonDtoRepository extends JpaRepository<Person, Long> {

    //TODO вместо FirstName должен быть FullName
    List<Person> findAllByFirstNameContainingIgnoreCase(String name, Pageable pageable);

    default List<Person> findTop3ByFullNameContainingIgnoreCase(String name) {
        return findAllByFirstNameContainingIgnoreCase(name, PageRequest.of(0, 3));
    }
}