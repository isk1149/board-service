package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.CommentUpdateDto;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/boards/comments")
    public ResponseEntity<?> comment(@RequestBody CommentInsertDto commentInsertDto) {
        BoardEntity boardEntity = boardService.getBoardForComment(commentInsertDto.getBoardId());
        CommentEntity commentEntity = commentService.comment(commentInsertDto, boardEntity);
        CommentDto response = new CommentDto(commentEntity);
        return ResponseEntity.ok().body(response);
    }

    /* delete는 deletionYesOrNo로 처리*/
//    @DeleteMapping("/boards/comments/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        commentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/boards/comments/recommendation")
    public ResponseEntity<?> recommend(@RequestBody CommentUpdateDto commentUpdateDto) {
        CommentEntity entity = commentService.recommend(commentUpdateDto);
        CommentDto response = new CommentDto(entity);
        return ResponseEntity.ok().body(response);
    }


}
