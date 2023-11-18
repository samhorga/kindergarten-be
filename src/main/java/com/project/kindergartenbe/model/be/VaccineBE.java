package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.model.dos.VaccineDO;
import lombok.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "vaccines") // Define the table name
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentBE student;

    public VaccineBE (VaccineDO vaccineDO) {
        this.vaccineName = vaccineDO.getVaccineName();
        this.vaccineDoses = vaccineDO.getVaccineDoses();
        this.vaccineDate = vaccineDO.getVaccineDate();
        this.setCreatedDate(vaccineDO.getCreatedDate());
        this.setEditedDate(vaccineDO.getEditedDate());
        this.setCreatedBy(vaccineDO.getCreatedBy());
        this.setLastEditedBy(vaccineDO.getLastEditedBy());
        this.setEditedDate(vaccineDO.getEditedDate());
    }
}
