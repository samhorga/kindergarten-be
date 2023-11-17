package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AllergyDO extends BaseDO{
    private String allergyName;
}
