package com.example.board.repository;

import com.example.board.entity.PostEntity;
import com.example.board.entity.QCommentEntity;
import com.example.board.entity.QPostEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.board.entity.QCommentEntity.commentEntity;
import static com.example.board.entity.QPostEntity.postEntity;

@Slf4j
public class PostRepositoryQueryDslImpl implements PostRepositoryQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryQueryDslImpl(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public PostEntity findTopViewCount() {
        PostEntity entity = jpaQueryFactory
                .select(postEntity)
                .from(postEntity)
                .orderBy(postEntity.viewCount.desc())
                .fetchFirst();
        return entity;
    }

    @Override
    public List<PostEntity> findPostsByHomeUsingQueryDsl(String boardId) {
        List<PostEntity> entities = jpaQueryFactory
                .select(postEntity).distinct()
                .from(postEntity)
                .leftJoin(postEntity.comments, commentEntity).fetchJoin()
                .where(postEntity.board.id.eq(boardId))
                .offset(0)
                .limit(5)
                .orderBy(postEntity.createdDateTime.desc())
                .fetch();
        return entities;
    }

    @Override
    public Page<PostEntity> findPostsByBoardUsingQueryDsl(String boardId, Pageable pageable) {
        JPAQuery<PostEntity> query = jpaQueryFactory
                .select(postEntity).distinct()
                .from(postEntity)
                .leftJoin(postEntity.comments, commentEntity).fetchJoin()
                .where(postEntity.board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        // Pageable에서 정렬 정보를 가져와 Querydsl에 적용
        for(Sort.Order order: pageable.getSort()){
            PathBuilder<QPostEntity> path = new PathBuilder<>(postEntity.getClass(), postEntity.getMetadata());
            query.orderBy(new OrderSpecifier(order.getDirection().isAscending()? Order.ASC:Order.DESC, path.get(order.getProperty())));
        }

        List<PostEntity> content = query.fetch();

        Long total = jpaQueryFactory
                .select(postEntity.count())
                .from(postEntity)
                .where(postEntity.board.id.eq(boardId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public PostEntity findPostUsingQueryDsl1(String postId) {
        PostEntity entity = jpaQueryFactory
                .select(postEntity).distinct()
                .from(postEntity)
                .leftJoin(postEntity.comments, commentEntity).fetchJoin()
                .where(postEntity.id.eq(postId))
                .fetchOne();
        return entity;
    }
}
