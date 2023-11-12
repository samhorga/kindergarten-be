package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdultBE extends BaseBE {
    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;
    private String email;
    private Boolean primary;
    private List<StudentBE> students = new ArrayList<>();
}
