package com.example.board.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Entity
@Table(name = "COMMENT", indexes = {@Index(name = "idx_comment_writer", columnList = "writer"),
                                    @Index(name = "idx_comment_sequence_number", columnList = "sequenceNumber")})
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, updatable = false, unique = true)
    private Long sequenceNumber;

    @Column(nullable = false)
    private String writer;

    @Lob
    @Column(nullable = false)
    private String comment;

    private Long recommendationCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn //default: 필드명 + _ + 참조하는 테이블의 기본 키 컬럼명
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "PARENT_COMMENT_ID", referencedColumnName = "ID")
    private CommentEntity parentComment;

    @OneToMany(mappedBy = "parentComment")
    List<CommentEntity> childrenComment = new ArrayList<>();

    public void increaseRecommendationCount() {
        ++recommendationCount;
    }

    public void decreaseRecommendationCount() {
        --recommendationCount;
    }
}
