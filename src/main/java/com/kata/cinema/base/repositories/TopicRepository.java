package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
