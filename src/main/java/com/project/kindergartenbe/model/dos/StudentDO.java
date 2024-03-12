package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
        this.createdDate = studentBE.getCreatedDate();
        this.editedDate = studentBE.getEditedDate();
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
        if(!CollectionUtils.isEmpty(adults)) {
            return adults.stream()
                    .map(AdultDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<NoteDO> mapNotes(List<NoteBE> noteBEList) {
        if(!CollectionUtils.isEmpty(noteBEList)) {
            return noteBEList.stream()
                    .map(NoteDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<AllergyDO> mapAllergies(List<AllergyBE> allergyBES) {
        if(!CollectionUtils.isEmpty(allergyBES)) {
            return allergyBES.stream()
                    .map(AllergyDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Set<VaccineDO> mapVaccines(Set<VaccineBE> vaccineBES) {
        if(!CollectionUtils.isEmpty(vaccineBES)) {
            return vaccineBES.stream()
                    .map(VaccineDO::new) // Assuming NoteBE has a constructor that takes NoteDO
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
