package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardInsertDto {
    //TODO - validation
    private String writer;
    private String subject;
    private String content;
}
