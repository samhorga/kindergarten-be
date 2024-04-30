package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.SchoolDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "schools") // Define the table name
@NoArgsConstructor
public class SchoolBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String directorFirstName;

    @Column(nullable = false)
    private String directorLastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<GroupBE> groups;

    public SchoolBE(SchoolDO schoolDO) {
        this.id = schoolDO.getId();
        this.name = schoolDO.getName();
        this.directorFirstName = schoolDO.getDirectorFirstName();
        this.directorLastName = schoolDO.getDirectorLastName();
        this.phoneNumber = schoolDO.getPhoneNumber();
        this.email = schoolDO.getEmail();
        this.password = schoolDO.getPassword();
        if (Objects.nonNull(schoolDO.getGroups())) {
            this.groups = schoolDO.getGroups().stream().map(GroupBE::new).collect(Collectors.toList());
        }
        this.createdDate = Objects.nonNull(schoolDO.getCreatedDate()) ? schoolDO.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(schoolDO.getEditedDate()) ? schoolDO.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(schoolDO.getLastEditedBy()) ? schoolDO.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(schoolDO.getCreatedBy()) ? schoolDO.getCreatedBy() : null;
    }
// Constructors, getters, and setters
}