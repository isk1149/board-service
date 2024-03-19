package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import com.example.board.entity.PostEntity;
import com.example.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class PostDto {
    private String id;
//    private String boardId;
//    private String boardName;
    private String writer;
    private String writerName;
    private String subject;
    private String content;
    private Long viewCount;
    private Long recommendationCount;
//    private List<CommentDto> comments;
    private String creator;
    private LocalDateTime createdDateTime;
    private String updater;
    private LocalDateTime updatedDateTime;

    public PostDto(final PostEntity postEntity) {
        this.id = postEntity.getId();
//        this.boardId = postEntity.getBoard().getId();
//        this.boardName = postEntity.getBoard().getBoardName();
        this.writer = postEntity.getWriter();
        this.subject = postEntity.getSubject();
        this.content = postEntity.getContent();
        this.viewCount = postEntity.getViewCount();
        this.recommendationCount = postEntity.getRecommendationCount();
//        if (postEntity.getComments() != null)
//            this.comments = commentEntitiesToDtos(postEntity.getComments());
        this.creator = postEntity.getCreator();
        this.createdDateTime = postEntity.getCreatedDateTime();
        this.updater = postEntity.getUpdater();
        this.updatedDateTime = postEntity.getUpdatedDateTime();
    }

//    private BoardDto boardEntityToDto(final BoardEntity boardEntity) {
//        return new BoardDto(boardEntity);
//    }
//
//    private List<CommentDto> commentEntitiesToDtos(final List<CommentEntity> entities) {
//         List<CommentDto> dtos = new ArrayList<>();
//        if (entities != null) {
//            entities.forEach(v -> dtos.add(new CommentDto(v)));
//        }
//        return dtos;
//    }
}
