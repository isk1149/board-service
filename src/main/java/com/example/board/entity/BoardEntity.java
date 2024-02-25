package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Entity
@Table(name = "BOARD", indexes = {@Index(name = "idx_board_writer", columnList = "writer"),
                                  @Index(name = "idx_board_sequence_number", columnList = "sequenceNumber")})
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, updatable = false, unique = true)
    private Long sequenceNumber;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    private Long viewCount;
    private Long recommendationCount;

    @OneToMany(mappedBy = "board")
    private List<CommentEntity> comments = new ArrayList<>();

    public void increaseViewCount() {
        ++viewCount;
    }

    public void increaseRecommendationCount() {
        ++recommendationCount;
    }

    public void decreaseRecommendationCount() {
        --recommendationCount;
    }

    public void delete() {
        setDeletionYesOrNo("1");
    }
}