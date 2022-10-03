package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.dto.response.CommentsResponseDto;
import com.kata.cinema.base.repositories.CommentsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsService {

    private final CommentsMapper commentsMapper;

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsMapper commentsMapper, CommentsRepository commentsRepository) {
        this.commentsMapper = commentsMapper;
        this.commentsRepository = commentsRepository;
    }

    public List<CommentsResponseDto> getCommentsByDate(Long id) {
        return commentsMapper.modelsToDTO(commentsRepository.findAll(Sort.by(Sort.Direction.DESC, "date")));
    }

    public void createComment(CommentsResponseDto commentsResponseDto) {
        commentsRepository.save(commentsMapper.toEntity(commentsResponseDto));
    }
}
