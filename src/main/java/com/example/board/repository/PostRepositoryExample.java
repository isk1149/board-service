package com.example.board.repository;

import com.example.board.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepositoryExample extends JpaRepository<PostEntity, String> {
    @Query("select p, b from PostEntity p join p.board b where b.id = :boardId")
    List<Object[]> findPostsForHome3(@Param("boardId") String boardId);
}
