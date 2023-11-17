package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.AllergyDO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "allergies") // Define the table name
@Getter
@Setter
public class AllergyBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "allergy_name")
    private String allergyName;

    @Column(name = "added_by")
    private String addedBy;

    public AllergyBE (AllergyDO allergyDO) {
        this.allergyName = allergyDO.getAllergyName();
        this.setCreatedDate(allergyDO.getCreatedDate());
        this.setEditedDate(allergyDO.getEditedDate());
        this.setCreatedBy(allergyDO.getCreatedBy());
        this.setLastEditedBy(allergyDO.getLastEditedBy());
        this.setEditedDate(allergyDO.getEditedDate());
    }
}
