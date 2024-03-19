package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostInsertDto {
    //TODO - validation
    private String writer;
    private String subject;
    private String content;
}
