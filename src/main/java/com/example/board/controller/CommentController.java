package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.dto.CommentInsertDto;
import com.example.board.dto.RecommendationDto;
import com.example.board.entity.CommentEntity;
import com.example.board.entity.PostEntity;
import com.example.board.service.CommentService;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

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

    private final PostService postService;
    private final CommentService commentService;

//    //TODO
//    @PostMapping("/boards/{boardId}/posts/{postId}/comments")
//    public ResponseEntity<?> comment(@PathVariable String boardId,
//                                     @PathVariable String postId,
//                                     @RequestBody CommentInsertDto commentInsertDto) {
//        PostEntity postEntity = postService.getPostForComment(postId);
//        CommentEntity commentEntity = commentService.comment(commentInsertDto, postEntity);
//        CommentDto response = new CommentDto(commentEntity);
//        return ResponseEntity.ok().body(response);
//    }

    /* delete는 deletionYesOrNo로 처리*/
//    @DeleteMapping("/boards/posts/comments/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        commentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

    /**
     * 댓글을 추천하거나 추천해제
     * @param commentId 댓글 ID
     * @param recommendationAction 추천(1) 혹은 추천해제(-1)
     * @return ResponseEntity
     */
    @Transactional
    @PatchMapping("/comments/{commentId}/recommendation")
    public ResponseEntity<?> recommend(@PathVariable String commentId,
                                       @RequestBody RecommendationDto recommendationAction) {

        Long recommendationCount = commentService.recommend(commentId, recommendationAction);
        return ResponseEntity.ok().body(recommendationCount);
    }

}
