package com.example.board.repository;

import com.example.board.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryQueryDsl {
    PostEntity findTopViewCount();

    List<PostEntity> findPostsByHomeUsingQueryDsl(String boardId);

    Page<PostEntity> findPostsByBoardUsingQueryDsl(String boardId, Pageable pageable);

    PostEntity findPostUsingQueryDsl1(String postId);
}
