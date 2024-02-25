package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentUpdateDto {
    private String id;
    private Long sequenceNumber;
    private String writer;
    private String comment;
    private Long recommendationCount;
}
