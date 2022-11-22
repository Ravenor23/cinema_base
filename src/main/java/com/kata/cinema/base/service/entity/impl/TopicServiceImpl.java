package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Topic;
import com.kata.cinema.base.repositories.TopicRepository;
import com.kata.cinema.base.service.entity.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }
}
