package com.example.board.repository;

import com.example.board.entity.BoardEntity;
import com.example.board.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findBoard() {
        List<BoardEntity> board = boardRepository.findBoard("7b9a7c55a9334d049e8a880cf216e28e");
        System.out.println("board.size() = " + board.size());
//        for (PostEntity post : posts) {
//            System.out.println("post.getSubject() = " + post.getSubject());
//        }

//        List<BoardEntity> boards1 = boardRepository.findFirst3ByOrderByCreatedDateTimeDesc();
//        for (BoardEntity boardEntity : boards1) {
//            System.out.println("boardEntity.getBoardName() = " + boardEntity.getBoardName());
//        }
//
//        List<BoardEntity> boards2 = boardRepository.findTop3By(Sort.by("createdDateTime"));
//        for (BoardEntity boardEntity : boards2) {
//            System.out.println("boardEntity.getBoardName() = " + boardEntity.getBoardName());
//        }
    }

    @Test
    public void findBoardById() {
        boardRepository.findById("7b9a7c55a9334d049e8a880cf216e28e");
    }
}