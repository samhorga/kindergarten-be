package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.AllergyDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "allergies") // Define the table name
@Getter
@Setter
@NoArgsConstructor
public class AllergyBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "allergy_name")
    private String allergyName;

    @Column(name = "added_by")
    private String addedBy;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentBE student;

    public AllergyBE (AllergyDO allergyDO) {
        this.allergyName = Objects.nonNull(allergyDO.getAllergyName()) ? allergyDO.getAllergyName() : null;
        this.createdDate = Objects.nonNull(allergyDO.getCreatedDate()) ? allergyDO.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(allergyDO.getEditedDate()) ? allergyDO.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(allergyDO.getLastEditedBy()) ? allergyDO.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(allergyDO.getCreatedBy()) ? allergyDO.getCreatedBy() : null;
    }
}
