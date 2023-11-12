package com.project.kindergartenbe.model.dos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDO extends BaseDO {
    private String firstName;
    private String lastName;
    private String classroom;
    private String schedule;
    private String dateOfBirth;
    private List<NoteDO> notes;
    private List<AllergyDO> allergies;
    private List<VaccineDO> vaccines;
    private List<AdultDO> adults;
}
