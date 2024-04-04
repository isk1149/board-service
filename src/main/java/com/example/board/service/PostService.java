package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.dto.PostInsertDto;
import com.example.board.dto.PostUpdateDto;
import com.example.board.dto.RecommendationDto;
import com.example.board.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostEntity post(String boardId, PostInsertDto postInsertDto);
    Page<PostEntity> getPostsByBoard(String boardId, int page, int size);
    Page<PostEntity> getPostsByBoardUsingPageable(String boardId, Pageable pageable);
    Page<PostEntity> getPostsByBoardUsingQueryDslPageable(String boardId, Pageable pageable);
    List<PostEntity> getPostsByHome(String boardId);
    PostDto getPost(String postId);
//    PostEntity getPostForComment(String postId);
    List<PostEntity> getPostsByWriter(String writer);

    PostEntity update(String postId, PostUpdateDto postUpdateDto, String userId);
    PostEntity recommend(String postId, RecommendationDto recommendationDto);
    void delete(String postId, String userId);
}
