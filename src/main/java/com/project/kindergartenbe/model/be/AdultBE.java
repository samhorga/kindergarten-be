package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.AdultDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "adults") // Define the table name
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "isAuthorizedForPickup")
    private Boolean isAuthorizedForPickup;

    @ManyToMany
    List<StudentBE> students;

    public AdultBE(AdultDO adultDO) {
        this.isAuthorizedForPickup = adultDO.getIsAuthorizedForPickup();
        this.firstName = adultDO.getFirstName();
        this.lastName = adultDO.getLastName();
        this.relationship = adultDO.getRelationship();
        this.phoneNumber = adultDO.getPhoneNumber();
        this.email = adultDO.getEmail();
        this.primary = adultDO.getPrimary();
        this.createdDate = Objects.nonNull(adultDO.getCreatedDate()) ? adultDO.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(adultDO.getEditedDate()) ? adultDO.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(adultDO.getLastEditedBy()) ? adultDO.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(adultDO.getCreatedBy()) ? adultDO.getCreatedBy() : null;
    }
}


