package com.example.board.repository;

import com.example.board.entity.PostEntity;

public interface PostRepositoryCustom {
    PostEntity findTopViewCount();
}
