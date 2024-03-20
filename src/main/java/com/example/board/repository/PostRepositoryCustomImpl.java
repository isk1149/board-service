package com.example.board.repository;

import com.example.board.entity.PostEntity;
import com.example.board.entity.QPostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public PostEntity findTopViewCount() {
        PostEntity postEntity = jpaQueryFactory
                .select(QPostEntity.postEntity)
                .from(QPostEntity.postEntity)
                .orderBy(QPostEntity.postEntity.viewCount.desc())
                .fetchFirst();
        return postEntity;
    }

}
