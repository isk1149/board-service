package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardInsertDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.dto.ResponseDto;
import com.example.board.entity.BoardEntity;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        log.info("handleEntityNotFoundException message={}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e) {
        log.info("handleDataAccessException message={}", e.getMessage());
        return ResponseEntity.internalServerError().build();
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred while using the database.");
    }

    //TODO - 500 error
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<?> handleInternalServerErrorException(DataAccessException e) {
//    }

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<?> register(@RequestBody BoardInsertDto boardInsertDto) {
        BoardEntity entity = boardService.register(boardInsertDto);
        BoardDto response = new BoardDto(entity);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        HashMap<String, String> map = new HashMap<>();
        map.put("data", "test111");
        ResponseDto<HashMap> response = ResponseDto.<HashMap>builder().data(map).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards")
    public ResponseEntity<?> getBoards() {
        List<BoardEntity> entities = boardService.getBoards();
        List<BoardDto> response = new ArrayList<>();
        entities.forEach(v -> response.add(new BoardDto(v)));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable String boardId) {
        BoardEntity entity = boardService.getBoard(boardId);
        BoardDto dto = new BoardDto(entity);
        ResponseDto<BoardDto> response = ResponseDto.<BoardDto>builder().data(dto).build();
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/boards/{boardId}")
    public ResponseEntity<?> update(@PathVariable String boardId,
                                    @RequestBody BoardUpdateDto boardUpdateDto) {
        BoardEntity entity = boardService.update(boardId, boardUpdateDto);
        BoardDto response = new BoardDto(entity);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<?> delete(@PathVariable String boardId) {
        boardService.delete(boardId);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

    //TODO - healthcheck


    //TODO - UUID
    @GetMapping("/uuid")
    public String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
