package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.NoteBE;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class NoteDO extends BaseDO {
    private String note;

    public NoteDO() {}

    public NoteDO(NoteBE noteBE) {
        this.note = Objects.nonNull(noteBE.getNote()) ? noteBE.getNote() : null;
        this.createdDate = Objects.nonNull(noteBE.getCreatedDate()) ? noteBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(noteBE.getEditedDate()) ? noteBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(noteBE.getLastEditedBy()) ? noteBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(noteBE.getCreatedBy()) ? noteBE.getCreatedBy() : null;
    }
}
