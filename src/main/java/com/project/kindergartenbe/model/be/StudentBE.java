package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentBE extends BaseBE {
    private String firstName;
    private String lastName;
    private String classroom;
    private String schedule;
    private String dateOfBirth;
    private List<NoteBE> notes;
    private List<AllergyBE> allergies;
    private List<VaccineBE> vaccines;
    private List<AdultBE> adults;
}
