package com.example.board.repository;

import com.example.board.entity.CommentSequenceNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentSequenceNumberRepository extends JpaRepository<CommentSequenceNumberEntity, Long> {
}
