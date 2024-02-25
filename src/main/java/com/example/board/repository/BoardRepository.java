package com.example.board.repository;

import com.example.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {
    List<BoardEntity> findByWriterOrderByIdDesc(String writer);
}
