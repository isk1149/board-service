package com.example.board.service;

import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardService {
    BoardEntity register(BoardInsertDto boardInsertDto);
    List<BoardEntity> getBoards();
    BoardEntity getBoard(String boardId);
    BoardEntity update(String boardId, BoardUpdateDto boardUpdateDto);
    void delete(String boardId);
}
