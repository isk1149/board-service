package com.example.board.service;

import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.CommentUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import com.example.board.entity.CommentSequenceNumberEntity;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.CommentSequenceNumberRepository;
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
    private final CommentSequenceNumberRepository commentSequenceNumberRepository;

    @Override
    public CommentEntity getComment(String id) {
        CommentEntity entity = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        return entity;
    }

    @Override
    public CommentEntity comment(CommentInsertDto commentInsertDto, BoardEntity boardEntity) {
        CommentSequenceNumberEntity commentSequenceNumberEntity = new CommentSequenceNumberEntity();
        CommentSequenceNumberEntity savedCommentSequenceNumberRepository = commentSequenceNumberRepository.save(commentSequenceNumberEntity);

        CommentEntity entity = CommentEntity.builder()
                .sequenceNumber(savedCommentSequenceNumberRepository.getSequenceNumber())
                .board(boardEntity)
                .writer(commentInsertDto.getWriter())
                .comment(commentInsertDto.getComment())
                .recommendationCount(0L)
                .creator(commentInsertDto.getWriter())
                .build();

        CommentEntity savedEntity = commentRepository.save(entity);
        return savedEntity;
    }

    @Transactional
    @Override
    public void delete(String id) {
        CommentEntity entity = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        commentRepository.delete(entity);
    }

    @Override
    public CommentEntity recommend(CommentUpdateDto commentUpdateDto) {
        CommentEntity entity = commentRepository.findById(commentUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentUpdateDto.getId()));

        if (commentUpdateDto.getRecommendationCount() > 0)
            entity.increaseRecommendationCount();
        else
            entity.decreaseRecommendationCount();

        return entity;
    }
}
