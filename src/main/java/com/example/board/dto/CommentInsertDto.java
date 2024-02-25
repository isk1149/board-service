package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentInsertDto {
    private String boardId;
    private String writer;
    private String comment;
}
