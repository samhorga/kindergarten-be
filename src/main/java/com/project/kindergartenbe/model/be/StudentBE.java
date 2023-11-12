package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students") // Define the table name
@Getter
@Setter
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
    private List<NoteBE> notes;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AllergyBE> allergies;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VaccineBE> vaccines;

    @ManyToMany
    @JoinTable(
            name = "student_adults",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "adult_id")
    )
    private List<AdultBE> adults;
}
