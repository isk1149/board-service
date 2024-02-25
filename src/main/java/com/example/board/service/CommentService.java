package com.example.board.service;

import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.CommentUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;

public interface CommentService {
    CommentEntity getComment(String id);
    CommentEntity comment(CommentInsertDto commentInsertDto, BoardEntity boardEntity);
    void delete(String id);
    CommentEntity recommend(CommentUpdateDto commentUpdateDto);
}
