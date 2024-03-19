package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import com.example.board.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BoardDto {
    private String id;
    //private List<PostDto> posts;
    private String boardName;
    private String description;

    public BoardDto(final BoardEntity boardEntity) {
        id = boardEntity.getId();
        //this.posts = postEntitesToDtos(boardEntity.getPosts());
        boardName = boardEntity.getBoardName();
        description = boardEntity.getDescription();
    }

//    public List<PostDto> postEntitesToDtos(final List<PostEntity> entities) {
//        List<PostDto> dtos = new ArrayList<>();
//        if (entities != null) {
//            entities.forEach(v->dtos.add(new PostDto(v)));
//        }
//        return dtos;
//    }
}
