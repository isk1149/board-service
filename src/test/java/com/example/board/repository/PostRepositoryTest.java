package com.example.board.repository;

import com.example.board.dto.PostDto;
import com.example.board.entity.PostEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.board.entity.QBoardEntity.boardEntity;
import static com.example.board.entity.QPostEntity.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostRepositoryTest {
    @Autowired
    public PostRepository postRepository;
    @PersistenceContext
    public EntityManager em;
    public JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void beforeEach() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void postsForHome() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        List<PostEntity> postsForHome = postRepository.findPostsByHome("7b9a7c55a9334d049e8a880cf216e28e", pageRequest);

        for (PostEntity postEntity : postsForHome)
            System.out.println("postEntity. = " + postEntity.getSubject());

    }

//    @Test
//    public void postsForHome2() {
//        List<PostEntity> postsForHome2 = postRepository.findPostsForHome2("7b9a7c55a9334d049e8a880cf216e28e");
//        for (PostEntity postEntity : postsForHome2) {
//            System.out.println("postEntity.getSubject() = " + postEntity.getSubject());
//            System.out.println("postEntity.getBoard().getBoardName() = " + postEntity.getBoard().getBoardName());
//        }
//    }

    @Test
    public void bulkUpdateViewCount() {
        int i = postRepository.updateViewCount("7b9a7c55a9334d049e8a880cf216e28e");
        System.out.println("i = " + i);
    }

    @Test
    public void findPosts() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        Page<PostEntity> page = postRepository.findPostsByPageable("7b9a7c55a9334d049e8a880cf216e28e", pageRequest);
        Page<PostDto> map = page.map((v) -> new PostDto(v));
        System.out.println("map = " + map.getContent());
        System.out.println("map.getTotalElements() = " + map.getTotalElements());
    }

    @Test
    public void queryDsl() {
        List<PostEntity> posts = jpaQueryFactory
                .selectFrom(postEntity)
                .where(postEntity.board.id.eq("7b9a7c55a9334d049e8a880cf216e28e"))
                .fetch();
//        for (PostEntity post : posts) {
//            System.out.println("post.getSubject() = " + post.getSubject());
//        }
    }

    @Test
    public void queryDsl2() {
        PostEntity postEntity1 = jpaQueryFactory
                .selectFrom(postEntity)
                .where(postEntity.id.eq("839ecf660f9a41038969fdc04f3d51bd"))
                .fetchOne();
        Assertions.assertThat(postEntity1.getSubject()).isEqualTo("Q&A게시글1");
//        for (PostEntity post : posts) {
//            System.out.println("post.getSubject() = " + post.getSubject());
//        }
    }

    @Test
    public void queryDslSearch1() {
        List<PostEntity> posts = jpaQueryFactory
                .selectFrom(postEntity)
                .where((postEntity.subject.like("Q&A%")
                        .and(postEntity.createdDateTime.loe(LocalDateTime.now())
                        )).or(postEntity.subject.like("지식%")))
                .fetch();
        Assertions.assertThat(posts.size()).isEqualTo(19);
    }

    @Test
    public void queryDslSearch2() {
        List<PostEntity> posts = jpaQueryFactory
                .selectFrom(postEntity)
                .where(postEntity.creator.eq("tester"),
                       postEntity.createdDateTime.loe(LocalDateTime.now()))
                .fetch();
        Assertions.assertThat(posts.size()).isEqualTo(26);
    }

    @Test
    public void queryDslSearch3() {
        int size = jpaQueryFactory
                .selectFrom(postEntity)
                .fetch().size();
        Assertions.assertThat(size).isEqualTo(28);
    }

    @Test
    public void queryDslSearch4() {
        List<PostEntity> posts = jpaQueryFactory
                .selectFrom(postEntity)
                .where(postEntity.board.id.eq("7b9a7c55a9334d049e8a880cf216e28e"))
                .orderBy(postEntity.createdDateTime.desc(), postEntity.creator.desc().nullsLast())
                .fetch();

    }

    @Test
    public void queryDslSearch5() {
        List<Tuple> result = jpaQueryFactory
                .select(postEntity.count(),
                        postEntity.viewCount.sum(),
                        postEntity.viewCount.avg(),
                        postEntity.viewCount.max(),
                        postEntity.viewCount.min())
                .from(postEntity)
                .where(postEntity.board.id.eq("7b9a7c55a9334d049e8a880cf216e28e"))
                .fetch();
        Tuple tuple = result.get(0);
        for (Tuple tuple1 : result) {
            System.out.println("tuple1.get(postEntity.count()) = " + tuple1.get(postEntity.count()));
            System.out.println("tuple1.get(postEntity.viewCount.sum()) = " + tuple1.get(postEntity.viewCount.sum()));
            System.out.println("tuple1.get(postEntity.viewCount.avg()) = " + tuple1.get(postEntity.viewCount.avg()));
            System.out.println("tuple1.get(postEntity.viewCount.max()) = " + tuple1.get(postEntity.viewCount.max()));
            System.out.println("tuple1.get(postEntity.viewCount.min()) = " + tuple1.get(postEntity.viewCount.min()));
        }
    }

    @Test
    public void queryDslSearch6() {
        List<Tuple> result = jpaQueryFactory
                .select(boardEntity.boardName, postEntity.viewCount.avg())
                .from(postEntity)
                .join(postEntity.board, boardEntity)
                .where(boardEntity.id.eq("7b9a7c55a9334d049e8a880cf216e28e"))
                .groupBy(postEntity.board)
                .fetch();
        Tuple tuple = result.get(0);
        System.out.println("tuple.get(boardEntity.boardName); = " + tuple.get(boardEntity.boardName));
        System.out.println("tuple.get(postEntity.viewCount.avg()) = " + tuple.get(postEntity.viewCount.avg()));
    }

    @Test
    public void queryDslSearch7() {
        List<Tuple> result = jpaQueryFactory
                .select(boardEntity.boardName, postEntity.viewCount.avg())
                .from(postEntity)
                .join(postEntity.board, boardEntity)
                .where(boardEntity.id.eq("7b9a7c55a9334d049e8a880cf216e28e"))
                .groupBy(postEntity.board)
                .fetch();
    }

    @Test
    public void queryDslSearch8() {
        List<PostEntity> result = jpaQueryFactory
                .selectFrom(postEntity)
                .join(postEntity.board, boardEntity)
                .where(boardEntity.id.eq("7b451b7e6cbe41e282f2d499c4fe2fcf"))
                .fetch();
        Assertions.assertThat(result).extracting("subject")
                .contains("bad news");
    }

    @Test
    public void queryDslSearch9() {
        List<Tuple> tuples = jpaQueryFactory
                .select(postEntity, boardEntity)
                .from(postEntity)
                .leftJoin(postEntity.board, boardEntity)
                .on(boardEntity.boardName.eq("지식"))
                .fetch();
        for (Tuple tuple : tuples) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void queryDslSearch10() {
        List<PostEntity> fetch = jpaQueryFactory
                .selectFrom(postEntity)
                .join(postEntity.board, boardEntity)
                .fetchJoin()
                .where(boardEntity.id.eq("7b451b7e6cbe41e282f2d499c4fe2fcf"))
                .fetch();

    }
}