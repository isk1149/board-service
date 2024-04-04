package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.entity.PostEntity;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

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

    @PostMapping("/boards/{boardId}/posts")
    public ResponseEntity<?> post(@PathVariable String boardId,
                                  @RequestBody PostInsertDto postInsertDto) {
        PostEntity entity = postService.post(boardId, postInsertDto);
        PostDto response = new PostDto(entity);
        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("/boards/{boardId}/posts")
//    public ResponseEntity<?> getPostsByBoard(@PathVariable String boardId,
//                                             @RequestParam(defaultValue = "0") int page,
//                                             @RequestParam(defaultValue = "3") int size) {
//        Page<PostEntity> pageEntity = postService.getPostsByBoard(boardId, page, size);
//        Page<PostDto> pageDto = pageEntity.map(PostDto::new); //v -> new PostDto(v)
//        ResponseDto<Page<PostDto>> response = ResponseDto.<Page<PostDto>>builder().data(pageDto).build();
//        return ResponseEntity.ok().body(response);
//    }

//    @GetMapping("/boards/{boardId}/posts")
//    public ResponseEntity<?> getPostsByBoardUsingPageable(@PathVariable String boardId, Pageable pageable) {
//        Page<PostEntity> pageEntity = postService.getPostsByBoardUsingPageable(boardId, pageable);
//        Page<PostDto> pageDto = pageEntity.map(PostDto::new); //v -> new PostDto(v)
//        ResponseDto<Page<PostDto>> response = ResponseDto.<Page<PostDto>>builder().data(pageDto).build();
//        return ResponseEntity.ok().body(response);
//    }

    @GetMapping("/boards/{boardId}/posts")
    public ResponseEntity<?> getPostsByBoardUsingQueryDslPageable(@PathVariable String boardId, Pageable pageable) {
        Page<PostEntity> pageEntity = postService.getPostsByBoardUsingQueryDslPageable(boardId, pageable);
        Page<PostHomeBoardDto> pageDto = pageEntity.map(PostHomeBoardDto::new); //v -> new PostDto(v)
        ResponseDto<Page<PostHomeBoardDto>> response = ResponseDto.<Page<PostHomeBoardDto>>builder().data(pageDto).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards/{boardId}/posts/home")
    public ResponseEntity<?> getPostsByHome(@PathVariable String boardId) {
        List<PostEntity> entities = postService.getPostsByHome(boardId);
        List<PostHomeBoardDto> dtos = new ArrayList<>();
        //entities.forEach(v -> dtos.add(new PostDto(v)));
        entities.forEach(v -> dtos.add(new PostHomeBoardDto(v)));

        ResponseDto<List<PostHomeBoardDto>> response = ResponseDto.<List<PostHomeBoardDto>>builder().data(dtos).build();
        log.info("response={}",response.getData());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable String postId) {
        PostDto dto = postService.getPost(postId);
        ResponseDto<PostDto> response = ResponseDto.<PostDto>builder().data(dto).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/posts/writer/{writer}")
    public ResponseEntity<?> getPostsByWriter(@PathVariable String writer) {
        List<PostEntity> entities = postService.getPostsByWriter(writer);
        List<PostDto> response = new ArrayList<>();
        entities.forEach(v -> response.add(new PostDto(v)));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<?> update(@PathVariable String postId,
                                    @RequestBody PostUpdateDto postUpdateDto) { //@RequestHeader("Authorization") String token
        String userId = ""; //userService.getUserIdFromToken(token);
        PostEntity entity = postService.update(postId, postUpdateDto, userId);
        PostDto response = new PostDto(entity);
        return ResponseEntity.ok().body(response);
    }


    @PatchMapping("/posts/{postId}/recommendation")
    public ResponseEntity<?> recommend(@PathVariable String postId,
                                       @RequestBody RecommendationDto recommendationDto) {
        PostEntity entity = postService.recommend(postId, recommendationDto);
        PostDto response = new PostDto(entity);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) { //@RequestHeader("Authorization") String token
        String userId = ""; //userService.getUserIdFromToken(token);
        postService.delete(id, userId);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

}
