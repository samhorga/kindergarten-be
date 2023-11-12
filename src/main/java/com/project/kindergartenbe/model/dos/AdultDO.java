package com.project.kindergartenbe.model.dos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdultDO extends BaseDO {
    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;
    private String email;
    private Boolean primary;
    private List<StudentDO> students = new ArrayList<>();
}
