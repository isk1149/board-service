package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "COMMENT_SEQUENCE_NUMBER")
@SequenceGenerator(
        name = "COMMENT_SEQUENCE_NUMBER_GENERATOR",
        sequenceName = "COMMENT_SEQUENCE", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class CommentSequenceNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQUENCE_NUMBER_GENERATOR")
    private Long sequenceNumber;
}
