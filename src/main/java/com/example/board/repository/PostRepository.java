package com.example.board.repository;

import com.example.board.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, String>, PostRepositoryQueryDsl {
    @Query("select p from PostEntity p join p.board b where b.id = :boardId")
    Page<PostEntity> findPostsByPageable(@Param("boardId") String boardId, Pageable pageable);
    @Query("select p from PostEntity p join p.board b where b.id = :boardId")
    List<PostEntity> findPostsByHome(@Param("boardId") String boardId, Pageable pageable);
    List<PostEntity> findByWriterOrderByIdDesc(String writer);
    @Modifying //(clearAutomatically = true)
    @Query("update PostEntity p set p.viewCount = p.viewCount + 1 where p.board.id = :boardId")
    int updateViewCount(@Param("boardId") String boardId);
}
