package com.project.kindergartenbe.model.be;

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
}
