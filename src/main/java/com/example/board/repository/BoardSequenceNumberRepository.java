package com.example.board.repository;

import com.example.board.entity.BoardSequenceNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardSequenceNumberRepository extends JpaRepository<BoardSequenceNumberEntity, Long> {
}
