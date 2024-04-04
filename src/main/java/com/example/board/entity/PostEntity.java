package com.example.board.entity;

import com.example.board.dto.PostUpdateDto;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.Builder;
import lombok.Builder.Default;
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
@Table(name = "POST", indexes = {@Index(name = "idx_post_writer", columnList = "writer"),
                                 @Index(name = "idx_fk_board_id", columnList = "board_id")})
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BoardEntity board;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AttachmentEntity> attachments = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments = new ArrayList<>();

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    private Long viewCount;
    private Long recommendationCount;

    public void updatePost(final PostUpdateDto postUpdateDto) {
        if (StringUtils.isNotBlank(postUpdateDto.getSubject()))
            this.subject = postUpdateDto.getSubject();
        if (StringUtils.isNotBlank(postUpdateDto.getContent()))
            this.content = postUpdateDto.getContent();
        if (StringUtils.isNotBlank(postUpdateDto.getWriter()))
            this.updater = postUpdateDto.getWriter();
    }

    public void increaseViewCount() {
        ++viewCount;
    }

    public void increaseRecommendationCount() {
        ++recommendationCount;
    }

    public void decreaseRecommendationCount() {
        if (recommendationCount > 0)
            --recommendationCount;
    }

    public void updateUpdater(String userId) {
        super.setUpdater(userId);
    }

//    // 게시글과 관련된 메소드 (생성자, getter, setter)
//    public void addAttachment(Attachment attachment) {
//        attachments.add(attachment);
//        attachment.setPost(this);
//    }
//
//    public void removeAttachment(Attachment attachment) {
//        attachments.remove(attachment);
//        attachment.setPost(null);
//    }
}