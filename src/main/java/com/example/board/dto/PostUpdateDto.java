package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostUpdateDto {
//    private String id;
    private String writer;
    private String subject;
    private String content;
    //private Long recommendationCount;
}
