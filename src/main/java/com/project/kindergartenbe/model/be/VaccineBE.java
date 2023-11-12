package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vaccines") // Define the table name
@Getter
@Setter
public class VaccineBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccine_doses")
    private String vaccineDoses;

    @Column(name = "vaccine_date")
    private String vaccineDate;

    @ManyToMany(mappedBy = "vaccines")
    private List<StudentBE> students;
}
