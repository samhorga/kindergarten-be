package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.AllergyBE;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AllergyDO extends BaseDO {
    private String allergyName;
    private Long id;

    public AllergyDO() {
    }

    public AllergyDO(AllergyBE allergyBE) {
        this.id = allergyBE.getId();
        this.allergyName = Objects.nonNull(allergyBE.getAllergyName()) ? allergyBE.getAllergyName() : null;
        this.createdDate = Objects.nonNull(allergyBE.getCreatedDate()) ? allergyBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(allergyBE.getEditedDate()) ? allergyBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(allergyBE.getLastEditedBy()) ? allergyBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(allergyBE.getCreatedBy()) ? allergyBE.getCreatedBy() : null;
    }
}
