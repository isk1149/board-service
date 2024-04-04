package com.example.board.dto;

import com.example.board.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * home화면에 post내용을 전달할 dto
 */
@Getter @Setter
@ToString
public class PostHomeBoardDto {
    private String id;
//    private String boardId;
//    private String boardName;
    private String writer;
    private String writerName;
    private String subject;
    private String content;
    private Long viewCount;
    private Long recommendationCount;
    private int commentCount;
    private String creator;
    private LocalDateTime createdDateTime;
    private String updater;
    private LocalDateTime updatedDateTime;

    public PostHomeBoardDto(final PostEntity postEntity) {
        this.id = postEntity.getId();
//        this.boardId = postEntity.getBoard().getId();
//        this.boardName = postEntity.getBoard().getBoardName();
        this.writer = postEntity.getWriter();
        this.subject = postEntity.getSubject();
        this.content = postEntity.getContent();
        this.viewCount = postEntity.getViewCount();
        this.recommendationCount = postEntity.getRecommendationCount();
        if (postEntity.getComments() != null)
            this.commentCount = postEntity.getComments().size();
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
