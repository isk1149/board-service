package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDto<T> {
    public String message;
    public T data;
}
