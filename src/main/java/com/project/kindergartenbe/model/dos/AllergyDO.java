package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class AllergyDO extends BaseDO{
    private String allergyName;

    public AllergyDO (AllergyBE allergyBE) {
        this.allergyName = Objects.nonNull(allergyBE.getAllergyName()) ? allergyBE.getAllergyName() : null;
        this.createdDate = Objects.nonNull(allergyBE.getCreatedDate()) ? allergyBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(allergyBE.getEditedDate()) ? allergyBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(allergyBE.getLastEditedBy()) ? allergyBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(allergyBE.getCreatedBy()) ? allergyBE.getCreatedBy() : null;
    }
}
