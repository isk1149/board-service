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
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class BaseEntity extends BaseTimeEntity {
    @Column(updatable = false)
    protected String creator;
    protected String updater;

    protected void setUpdater(String updater) {
        this.updater = updater;
    }

}
