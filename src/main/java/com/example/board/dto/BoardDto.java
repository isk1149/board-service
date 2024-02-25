package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BoardDto {
    private String id;
    private Long sequenceNumber;
    private String writer;
    private String subject;
    private String content;
    private Long viewCount;
    private Long recommendationCount;
    private List<CommentDto> comments;
    private String creator;
    private LocalDateTime createdDateTime;
    private String updater;
    private LocalDateTime updatedDateTime;

    public BoardDto(final BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.sequenceNumber = boardEntity.getSequenceNumber();
        this.writer = boardEntity.getWriter();
        this.subject = boardEntity.getSubject();
        this.content = boardEntity.getContent();
        this.viewCount = boardEntity.getViewCount();
        this.recommendationCount = boardEntity.getRecommendationCount();
        this.comments = entitiesToDtos(boardEntity.getComments());
        this.creator = boardEntity.getCreator();
        this.createdDateTime = boardEntity.getCreatedDateTime();
        this.updater = boardEntity.getUpdater();
        this.updatedDateTime = boardEntity.getUpdatedDateTime();
    }

    private List<CommentDto> entitiesToDtos(List<CommentEntity> list) {
         List<CommentDto> dtos = new ArrayList<>();
        if (list != null) {
            list.forEach(v -> dtos.add(new CommentDto(v)));
        }
        return dtos;
    }
}
