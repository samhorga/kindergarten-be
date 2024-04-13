package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "students") // Define the table name
@Getter
@Setter
@NoArgsConstructor
public class StudentBE extends BaseBE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "classroom")
    private String classroom;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<NoteBE> notes = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<AllergyBE> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<VaccineBE> vaccines = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "adult_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "adult_id"))
    List<AdultBE> adults;

    public StudentBE(StudentDO studentDO) {
        this.firstName = studentDO.getFirstName();
        this.lastName = studentDO.getLastName();
        this.classroom = studentDO.getClassroom();
        this.schedule = studentDO.getSchedule() ;
        this.dateOfBirth = Objects.nonNull(studentDO.getDateOfBirth()) ? studentDO.getDateOfBirth() : null;
        this.notes = Objects.nonNull(studentDO.getNotes()) ? mapNotes(studentDO.getNotes()) : null;
        this.allergies = Objects.nonNull(studentDO.getNotes()) ? mapAllergies(studentDO.getAllergies()) : null;
        this.vaccines = Objects.nonNull(studentDO.getVaccines()) ? mapVaccines(studentDO.getVaccines()) : null;
        this.adults = Objects.nonNull(studentDO.getAdults()) ? mapAdults(studentDO.getAdults()) : null;
    }

    private List<NoteBE> mapNotes(List<NoteDO> noteDOList) {
        return noteDOList.stream()
                .map(NoteBE::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }

    private List<AllergyBE> mapAllergies(List<AllergyDO> allergyDOList) {
        return allergyDOList.stream()
                .map(AllergyBE::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }

    private Set<VaccineBE> mapVaccines(Set<VaccineDO> vaccineDOList) {
        return vaccineDOList.stream()
                .map(VaccineBE::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toSet());
    }

    private List<AdultBE> mapAdults(List<AdultDO> adultDOS) {
        return adultDOS.stream()
                .map(AdultBE::new)
                .collect(Collectors.toList());
    }
}
