package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailContainingIgnoreCase(String email);

    @Query(value = "select u from User u join fetch u.roles where u.email=?1")
    Optional<User> findByEmail(String email);


}
