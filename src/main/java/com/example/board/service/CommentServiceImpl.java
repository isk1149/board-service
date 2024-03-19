package com.example.board.service;

import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.RecommendationDto;
import com.example.board.entity.CommentEntity;
import com.example.board.entity.PostEntity;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentEntity comment(CommentInsertDto commentInsertDto, PostEntity postEntity) {
        CommentEntity entity = CommentEntity.builder()
                .post(postEntity)
                .writer(commentInsertDto.getWriter())
                .comment(commentInsertDto.getComment())
                .recommendationCount(0L)
                .creator(commentInsertDto.getWriter())
                .build();

        CommentEntity savedEntity = commentRepository.save(entity);
        return savedEntity;
    }

    @Override
    public CommentEntity getComment(String commentId) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
        return entity;
    }

    @Transactional
    @Override
    public void delete(String commentId) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
        commentRepository.delete(entity);
    }

    @Override
    public Long recommend(String commentId, RecommendationDto recommendationAction) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        if (recommendationAction.getValue() > 0)
            entity.increaseRecommendationCount();
        else
            entity.decreaseRecommendationCount();

        return entity.getRecommendationCount();
    }
}
