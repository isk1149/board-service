package com.example.board.repository;

import com.example.board.entity.CommentEntity;
import com.example.board.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryQueryDslImplTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void findTopViewCountTest() {
        PostEntity post = postRepository.findTopViewCount();
        System.out.println("post.getSubject() = " + post.getSubject());
        System.out.println("post.getViewCount() = " + post.getViewCount());
    }

    @Test
    public void findPostsByHomeUsingQueryDsl() {
        List<PostEntity> posts = postRepository.findPostsByHomeUsingQueryDsl("7b9a7c55a9334d049e8a880cf216e28e");
        for (PostEntity post : posts) {
            System.out.println("post.getSubject() = " + post.getSubject());
            for (CommentEntity comment : post.getComments()) {
                System.out.println("comment.getComment() = " + comment.getComment());
            }
        }
    }

    @Test
    public void findPostsByBoardUsingQueryDsl() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        Page<PostEntity> postsByBoardUsingQueryDsl = postRepository.findPostsByBoardUsingQueryDsl("7b9a7c55a9334d049e8a880cf216e28e", pageRequest);
        System.out.println("postsByBoardUsingQueryDsl.getContent() = " + postsByBoardUsingQueryDsl.getContent());
//        for (PostEntity postEntity : postsByBoardUsingQueryDsl) {
//            System.out.println("postEntity.getSubject() = " + postEntity.getSubject());
//            for (CommentEntity comment : postEntity.getComments()) {
//                System.out.println("comment.getComment() = " + comment.getComment());
//            }
//        }
        System.out.println("postsByBoardUsingQueryDsl.getNumber() = " + postsByBoardUsingQueryDsl.getNumber());
        System.out.println("postsByBoardUsingQueryDsl.getSize() = " + postsByBoardUsingQueryDsl.getSize());
        System.out.println("postsByBoardUsingQueryDsl.getTotalPages() = " + postsByBoardUsingQueryDsl.getTotalPages());
        System.out.println("postsByBoardUsingQueryDsl.getTotalElements() = " + postsByBoardUsingQueryDsl.getTotalElements());
    }

    @Test
    public void findPostUsingQueryDsl() {
        System.out.println("postRepository.getClass() = " + postRepository.getClass());

        PostEntity entity = postRepository.findPostUsingQueryDsl1("1009742sddac4abcd9c3412d98b78457");
        System.out.println("entity.getSubject() = " + entity.getSubject());
//        System.out.println("entity.getComments() = " + entity.getComments());
//        for (CommentEntity comment : entity.getComments()) {
//            System.out.println("comment.getComment() = " + comment.getComment());
//            if (comment.getChildrenComment().size() != 0)
//                System.out.println("comment.getChildrenComment().get(0).getComment() = " + comment.getChildrenComment().get(0).getComment());
//        }
    }

}