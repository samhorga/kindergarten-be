package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.VaccineBE;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class VaccineDO extends BaseDO {
    private String vaccineName;
    private String vaccineDoses;
    private String vaccineDate;
    private List<StudentDO> students;
    private Long id;

    public VaccineDO() {}

    public VaccineDO (VaccineBE vaccineBE) {
        this.id = vaccineBE.getId();
        this.vaccineName = vaccineBE.getVaccineName();
        this.vaccineDoses = vaccineBE.getVaccineDoses();
        this.vaccineDate = vaccineBE.getVaccineDate();
        this.createdDate = Objects.nonNull(vaccineBE.getCreatedDate()) ? vaccineBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(vaccineBE.getEditedDate()) ? vaccineBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(vaccineBE.getLastEditedBy()) ? vaccineBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(vaccineBE.getCreatedBy()) ? vaccineBE.getCreatedBy() : null;
    }
}
