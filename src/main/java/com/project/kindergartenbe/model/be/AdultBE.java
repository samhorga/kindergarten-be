package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adults") // Define the table name
@Getter
@Setter
public class AdultBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "is_primary")
    private Boolean primary;

    @OneToMany(mappedBy = "adult", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<StudentBE> students = new ArrayList<>();
}


