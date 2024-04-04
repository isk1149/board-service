package com.example.board.entity;

import com.example.board.dto.BoardUpdateDto;
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
@Table(name = "BOARD")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Default
    @OneToMany(mappedBy = "board")
    private List<PostEntity> posts = new ArrayList<>();

    @Column(nullable = false)
    private String boardName;

    @Column(nullable = false)
    private String description;

    public void updateBoard(final BoardUpdateDto boardUpdateDto) {
        if (StringUtils.isNotBlank(boardUpdateDto.getBoardName()))
            this.boardName = boardUpdateDto.getBoardName();
        if (StringUtils.isNotBlank(boardUpdateDto.getDescription())) {
            this.description = boardUpdateDto.getDescription();
        }
    }

    public void updateUpdater(String userId) {
        super.setUpdater(userId);
    }

}
