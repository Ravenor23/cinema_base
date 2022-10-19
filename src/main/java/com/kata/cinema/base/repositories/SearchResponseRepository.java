package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchResponseRepository extends JpaRepository<SearchResponseDto,Long> {

    @Query("select c from Collection c where c.name like concat('%',:query,'%')")
    List<SearchResponseDto> search(String query);
}
