package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class BaseEntity {
    @Column(updatable = false)
    protected String creator;
    @Column(updatable = false)
    protected LocalDateTime createdDateTime;

    protected String updater;
    protected LocalDateTime updatedDateTime;
    @Column(length = 1)
    protected String deletionYesOrNo;
    protected String deleter;
    protected LocalDateTime deletedDateTime;

    @PrePersist
    public void prePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.deletionYesOrNo = "0";
    }

    protected void setDeletionYesOrNo(String deletionYesOrNo) {
        this.deletionYesOrNo = deletionYesOrNo;
    }

    protected void setDeletionYes() {
        deletionYesOrNo = "1";
    }

    protected void setDeleter(String deleter) {
        this.deleter = deleter;
    }

    protected void setDeletedDateTime(LocalDateTime deletedDateTime) {
        this.deletedDateTime = deletedDateTime;
    }

}
