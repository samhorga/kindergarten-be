package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.StudentBE;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class AdultDO extends BaseDO {
    private Long id;
    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;
    private String email;
    private Boolean primary;
    private List<StudentDO> students = new ArrayList<>();
    private Boolean isAuthorizedForPickup;

    public AdultDO() {
    }

    public AdultDO(AdultBE adultBE) {
        this.id = adultBE.getId();
        this.isAuthorizedForPickup = Objects.nonNull(adultBE.getIsAuthorizedForPickup()) ? adultBE.getIsAuthorizedForPickup() : null;
        this.firstName = Objects.nonNull(adultBE.getFirstName()) ? adultBE.getFirstName() : null;
        this.lastName = Objects.nonNull(adultBE.getLastName()) ? adultBE.getLastName() : null;
        this.relationship =Objects.nonNull(adultBE.getRelationship()) ? adultBE.getRelationship() : null;
        this.phoneNumber = Objects.nonNull(adultBE.getPhoneNumber()) ? adultBE.getPhoneNumber() : null;
        this.email = Objects.nonNull(adultBE.getEmail()) ? adultBE.getEmail() : null;
        this.primary = Objects.nonNull(adultBE.getPrimary()) ? adultBE.getPrimary() : null;
        this.createdDate = Objects.nonNull(adultBE.getCreatedDate()) ? adultBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(adultBE.getEditedDate()) ? adultBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(adultBE.getLastEditedBy()) ? adultBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(adultBE.getCreatedBy()) ? adultBE.getCreatedBy() : null;
//        this.students = Objects.nonNull(adultBE.getStudents()) ? mapStudents(adultBE.getStudents()) : null;
    }

//    private List<StudentDO> mapStudents(List<StudentBE> students) {
//        return students.stream().map(StudentDO::new).collect(Collectors.toList());
//    }
}
