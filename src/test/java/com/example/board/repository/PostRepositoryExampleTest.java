package com.example.board.repository;

import com.example.board.entity.BoardEntity;
import com.example.board.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostRepositoryExampleTest {
    @Autowired
    public PostRepositoryExample postRepositoryExample;
    @PersistenceContext
    public EntityManager em;
    public JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void beforeEach() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void postsForHome3() {
        List<Object[]> postsForHome3 = postRepositoryExample.findPostsForHome3("7b9a7c55a9334d049e8a880cf216e28e");
        for (Object[] result : postsForHome3) {
            PostEntity post = (PostEntity) result[0];
            BoardEntity board = (BoardEntity) result[1];
            // 여기서 name을 출력
            System.out.println("post.getSubject() = " + post.getSubject());
            System.out.println("post.getBoardName() = " + board.getBoardName());
        }
    }
}