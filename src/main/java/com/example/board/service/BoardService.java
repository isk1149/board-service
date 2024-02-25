package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.BoardEntity;

import java.util.List;

public interface BoardService {
    List<BoardEntity> getBoards();
    BoardEntity getBoard(String id);
    BoardEntity getBoardForComment(String id);
    List<BoardEntity> getBoardsByWriter(String writer);
    BoardEntity post(BoardInsertDto boardInsertDto);
    void delete(String id);
    BoardEntity recommend(BoardUpdateDto boardUpdateDto);
}
