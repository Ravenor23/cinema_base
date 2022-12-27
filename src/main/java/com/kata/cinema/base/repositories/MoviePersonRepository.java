package com.kata.cinema.base.repositories;
import com.kata.cinema.base.models.dto.response.DirectorMovieDto;
import com.kata.cinema.base.models.dto.response.RoleMovieDto;
import com.kata.cinema.base.models.entity.MoviePerson;
import com.kata.cinema.base.models.entity.MoviePerson.Id;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePersonRepository extends JpaRepository<MoviePerson, Id> {

    @Query("from MoviePerson WHERE professions.id = :professionId")
    List<MoviePerson> getByProfessionId(long professionId);

    @Query(
        "select new com.kata.cinema.base.models.dto.response.RoleMovieDto(mp.movie.id, mp.nameRole) from MoviePerson mp where mp.movie.id in :movieId AND mp.type = 'MAIN_CHARACTER'")
    List<RoleMovieDto> getRoles(List<Long> movieId);

    @Query(
        "select new com.kata.cinema.base.models.dto.response.DirectorMovieDto(mp.movie.id, mp.person.firstName) from MoviePerson mp where mp.movie.id in :movieId AND mp.professions.name = 'режиссер'")
    List<DirectorMovieDto> getDirectorName(List<Long> movieId);
}
