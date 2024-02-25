package com.example.board.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOARD_SEQUENCE_NUMBER")
@SequenceGenerator(
        name = "BOARD_SEQUENCE_NUMBER_GENERATOR",
        sequenceName = "BOARD_SEQUENCE", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class BoardSequenceNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQUENCE_NUMBER_GENERATOR")
    private Long sequenceNumber;
}
