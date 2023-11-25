package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.NoteDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notes") // Define the table name
@Getter
@Setter
@NoArgsConstructor
public class NoteBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note_text")
    private String note;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentBE student;

    public NoteBE(NoteDO noteDO) {
        this.note = Objects.nonNull(noteDO.getNote()) ? noteDO.getNote() : null;
        this.createdDate = Objects.nonNull(noteDO.getCreatedDate()) ? noteDO.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(noteDO.getEditedDate()) ? noteDO.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(noteDO.getLastEditedBy()) ? noteDO.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(noteDO.getCreatedBy()) ? noteDO.getCreatedBy() : null;
    }
}
