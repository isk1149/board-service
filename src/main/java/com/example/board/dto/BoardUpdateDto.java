package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardUpdateDto {
    private String id;
    private Long sequenceNumber;
    private String writer;
    private String subject;
    private String content;
    private Long recommendationCount;
}
