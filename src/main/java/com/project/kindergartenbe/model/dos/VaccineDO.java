package com.project.kindergartenbe.model.dos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VaccineDO extends BaseDO {
    private String vaccineName;
    private String vaccineDoses;
    private String vaccineDate;
    private List<StudentDO> students;
}
