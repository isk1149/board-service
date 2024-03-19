package com.example.board.repository;

import com.example.board.entity.BoardEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    @Query("select b from BoardEntity b join fetch b.posts where b.id = :boardId")
    BoardEntity findBoard(@Param("boardId") String boardId);

    List<BoardEntity> findFirst3ByOrderByCreatedDateTimeDesc();
    List<BoardEntity> findTop3By(Sort sort); //boardRepository.findTop3By(Sort.by("createdDateTime"));

}
