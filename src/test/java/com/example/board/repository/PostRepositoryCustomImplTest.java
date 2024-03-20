package com.example.board.repository;

import com.example.board.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryCustomImplTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void findTopViewCountTest() {
        PostEntity post = postRepository.findTopViewCount();
        System.out.println("post.getSubject() = " + post.getSubject());
        System.out.println("post.getViewCount() = " + post.getViewCount());

    }
}