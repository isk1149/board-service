package com.example.board.service;

import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.BoardSequenceNumberEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.BoardSequenceNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardSequenceNumberRepository boardSequenceNumberRepository;

    @Override
    public List<BoardEntity> getBoards() {
        List<BoardEntity> entities = boardRepository.findAll();
        return entities;
    }

    @Transactional
    @Override
    public BoardEntity getBoard(String id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
        entity.increaseViewCount();
        return entity;
    }


    @Override
    public BoardEntity getBoardForComment(String id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
        return entity;
    }

    @Override
    public List<BoardEntity> getBoardsByWriter(String writer) {
        List<BoardEntity> entities = boardRepository.findByWriterOrderByIdDesc(writer);
        return entities;
    }

    @Transactional
    @Override
    public BoardEntity post(BoardInsertDto boardInsertDto) {
        BoardSequenceNumberEntity boardSequenceNumberEntity = new BoardSequenceNumberEntity();
        BoardSequenceNumberEntity savedBoardSequenceNumberEntity
                = boardSequenceNumberRepository.save(boardSequenceNumberEntity);

        BoardEntity entity = BoardEntity.builder()
                .sequenceNumber(savedBoardSequenceNumberEntity.getSequenceNumber())
                .writer(boardInsertDto.getWriter())
                .subject(boardInsertDto.getSubject())
                .content(boardInsertDto.getContent())
                .viewCount(0L)
                .recommendationCount(0L)
                .creator(boardInsertDto.getWriter())
                .build();

        BoardEntity savedEntity = boardRepository.save(entity);
        return savedEntity;
    }

    @Transactional
    @Override
    public void delete(String id) {
        BoardEntity entity = boardRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
        entity.delete();
    }

    @Transactional
    @Override
    public BoardEntity recommend(BoardUpdateDto boardUpdateDto) {
        BoardEntity entity = boardRepository.findById(boardUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardUpdateDto.getId()));
        if (boardUpdateDto.getRecommendationCount() > 0)
            entity.increaseRecommendationCount();
        else
            entity.decreaseRecommendationCount();

        return entity;
    }
}
