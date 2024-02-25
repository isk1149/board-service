package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        log.warn("handleEntityNotFoundException message={}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e) {
        log.warn("handleDataAccessException message={}", e.getMessage());
        return ResponseEntity.internalServerError().build();
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred while using the database.");
    }

    //TODO - 500 error
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<?> handleInternalServerErrorException(DataAccessException e) {
//    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardDto>> getBoards() {
        List<BoardEntity> entities = boardService.getBoards();
        List<BoardDto> response = new ArrayList<>();
        entities.forEach(v -> response.add(new BoardDto(v)));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable String id) {
        BoardEntity entity = boardService.getBoard(id);
        BoardDto response = new BoardDto(entity);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards/writer/{writer}") //writer/{writerId}/posts
    public ResponseEntity<?> getBoardsByWriter(@PathVariable String writer) {
        List<BoardEntity> entities = boardService.getBoardsByWriter(writer);
        List<BoardDto> response = new ArrayList<>();
        entities.forEach(v -> response.add(new BoardDto(v)));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/boards")
    public ResponseEntity<?> post(@RequestBody BoardInsertDto boardInsertDto) {
        BoardEntity entity = boardService.post(boardInsertDto);
        BoardDto response = new BoardDto(entity);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

    @PostMapping("/boards/recommendation")
    public ResponseEntity<?> recommend(@RequestBody BoardUpdateDto boardUpdateDto) {
        BoardEntity entity = boardService.recommend(boardUpdateDto);
        BoardDto response = new BoardDto(entity);
        return ResponseEntity.ok().body(response);
    }
}
