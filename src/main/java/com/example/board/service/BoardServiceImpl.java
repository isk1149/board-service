package com.example.board.service;

import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public BoardEntity register(BoardInsertDto boardInsertDto) {
        BoardEntity entity = BoardEntity.builder()
                .boardName(boardInsertDto.getBoardName())
                .description(boardInsertDto.getDescription())
                .creator("test")
                .build();

        BoardEntity savedEntity = boardRepository.save(entity);
        return savedEntity;
    }

    @Override
    public List<BoardEntity> getBoards() {
        List<BoardEntity> entities = boardRepository.findAll();
        return entities;
    }

    @Override
    public BoardEntity getBoard(String boardId) {
        BoardEntity entity = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
        return entity;
    }

    @Transactional
    @Override
    public BoardEntity update(String boardId, BoardUpdateDto boardUpdateDto) {

        BoardEntity entity = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
        /*
        BeanUtils.copyProperties(boardUpdateDto, entity, getNullPropertyNames(boardUpdateDto));
         */
        entity.updateBoard(boardUpdateDto);
        entity.updateUpdater("test");
        return entity;
    }

    @Transactional
    @Override
    public void delete(String boardId) {
        BoardEntity entity = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
        boardRepository.delete(entity);
    }
}
