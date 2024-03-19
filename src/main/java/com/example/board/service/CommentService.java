package com.example.board.service;

import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.CommentUpdateDto;
import com.example.board.dto.RecommendationDto;
import com.example.board.entity.PostEntity;
import com.example.board.entity.CommentEntity;

public interface CommentService {
    CommentEntity getComment(String commentId);
    CommentEntity comment(CommentInsertDto commentInsertDto, PostEntity postEntity);
    void delete(String commentId);
    Long recommend(String commentId, RecommendationDto recommendationDto);
}
