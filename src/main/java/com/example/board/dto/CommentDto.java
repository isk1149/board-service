package com.example.board.dto;

import com.example.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDto {
    private String id;
    private String writer;
    private String comment;
    private Long recommendationCount;
    private String creator;
    private LocalDateTime createdDateTime;
    private String updater;
    private LocalDateTime updatedDateTime;

    public CommentDto(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.writer = commentEntity.getWriter();
        this.comment = commentEntity.getComment();
        this.recommendationCount = commentEntity.getRecommendationCount();
        this.creator = commentEntity.getCreator();
        this.createdDateTime = commentEntity.getCreatedDateTime();
        this.updater = commentEntity.getUpdater();
        this.updatedDateTime = commentEntity.getUpdatedDateTime();
    }
}
