package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.GroupBE;
import com.project.kindergartenbe.model.be.SchoolBE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SchoolDO extends BaseDO {
    private Long id;
    private String name;
    private String directorFirstName;
    private String directorLastName;
    private String phoneNumber;
    private String email;
    private String password;
    private List<GroupDO> groups;

    public SchoolDO(SchoolBE schoolBE) {
        this.id = schoolBE.getId();
        this.name = schoolBE.getName();
        this.directorFirstName = schoolBE.getDirectorFirstName();
        this.directorLastName = schoolBE.getDirectorLastName();
        this.phoneNumber = schoolBE.getPhoneNumber();
        this.email = schoolBE.getEmail();
        this.password = schoolBE.getPassword();
        if (Objects.nonNull(schoolBE.getGroups())) {
            this.groups = schoolBE.getGroups().stream().map(GroupDO::new).collect(Collectors.toList());
        }
        this.createdDate = Objects.nonNull(schoolBE.getCreatedDate()) ? schoolBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(schoolBE.getEditedDate()) ? schoolBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(schoolBE.getLastEditedBy()) ? schoolBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(schoolBE.getCreatedBy()) ? schoolBE.getCreatedBy() : null;
    }
}
