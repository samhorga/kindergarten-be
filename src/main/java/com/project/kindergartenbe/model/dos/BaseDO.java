package com.project.kindergartenbe.model.dos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDO {
    private String createdDate;
    private String editedDate;
    private String lastEditedBy;
    private String createdBy;
}