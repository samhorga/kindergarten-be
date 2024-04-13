package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "adult_student")
@Embeddable
public class AdultStudentBE {
    @ManyToOne
    @JoinColumn(name = "adult_id")
    private AdultBE adult;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentBE student;
}
