package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@Getter
@NoArgsConstructor
public class BaseTimeEntity {
    @Column(updatable = false)
    protected LocalDateTime createdDateTime;
    protected LocalDateTime updatedDateTime;

    @PrePersist
    protected void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDateTime = now;
        this.updatedDateTime = now;
    }

    /**
     * 조회 수 올리는 행위에도 updatedDateTime이 적용되어 주석처리 해야할지
     */
    @PreUpdate
    protected void preUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }
}
