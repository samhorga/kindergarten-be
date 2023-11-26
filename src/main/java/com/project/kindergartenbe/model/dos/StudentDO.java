package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class StudentDO extends BaseDO {
    private Long id;
    private String firstName;
    private String lastName;
    private String classroom;
    private String schedule;
    private String dateOfBirth;
    private List<NoteDO> notes;
    private List<AllergyDO> allergies;
    private Set<VaccineDO> vaccines;
    private List<AdultDO> adults;
    private Set<StudentBE> students = new HashSet<>();

    public StudentDO(StudentBE studentBE) {
        this.id = studentBE.getId();
        this.firstName = studentBE.getFirstName();
        this.lastName = studentBE.getLastName();
        this.classroom = studentBE.getClassroom();
        this.schedule = studentBE.getSchedule() ;
        this.dateOfBirth = Objects.nonNull(studentBE.getDateOfBirth()) ? studentBE.getDateOfBirth() : null;
        this.notes = Objects.nonNull(studentBE.getNotes()) ? mapNotes(studentBE.getNotes()) : null;
        this.allergies = Objects.nonNull(studentBE.getNotes()) ? mapAllergies(studentBE.getAllergies()) : null;;
        this.vaccines = Objects.nonNull(studentBE.getVaccines()) ? mapVaccines(studentBE.getVaccines()) : null;;
        this.adults = Objects.nonNull(studentBE.getVaccines()) ? mapAdults(studentBE.getAdults()) : null;;
    }

    private List<AdultDO> mapAdults(List<AdultBE> adults) {
        return adults.stream()
                .map(AdultDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }

    private List<NoteDO> mapNotes(List<NoteBE> noteBEList) {
        return noteBEList.stream()
                .map(NoteDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }

    private List<AllergyDO> mapAllergies(List<AllergyBE> allergyBES) {
        return allergyBES.stream()
                .map(AllergyDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }

    private Set<VaccineDO> mapVaccines(Set<VaccineBE> vaccineBES) {
        return vaccineBES.stream()
                .map(VaccineDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toSet());
    }
}
