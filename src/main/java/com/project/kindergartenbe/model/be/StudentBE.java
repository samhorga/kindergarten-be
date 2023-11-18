package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.model.dos.NoteDO;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.model.dos.VaccineDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteBE> notes = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AllergyBE> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VaccineBE> vaccines = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "student_adults",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "adult_id")
    )
    private List<AdultBE> adults = new ArrayList<>();

    public StudentBE(StudentDO studentDO) {
        this.firstName = studentDO.getFirstName();
        this.lastName = studentDO.getLastName();
        this.classroom = studentDO.getClassroom();
        this.schedule = studentDO.getSchedule();
        this.dateOfBirth = studentDO.getDateOfBirth();
        this.notes = mapNotes(studentDO.getNotes());
        this.allergies = mapAllergies(studentDO.getAllergies());
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

    private List<VaccineBE> mapVaccines(List<VaccineDO> vaccineDOList) {
        return vaccineDOList.stream()
                .map(VaccineBE::new) // Assuming NoteBE has a constructor that takes NoteDO
                .collect(Collectors.toList());
    }
}
