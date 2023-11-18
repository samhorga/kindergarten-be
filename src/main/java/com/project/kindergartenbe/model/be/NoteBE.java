package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.NoteDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
        this.note = noteDO.getNote();
    }
}
