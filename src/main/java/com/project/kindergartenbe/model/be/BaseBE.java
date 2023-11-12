package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class BaseBE {

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "edited_date")
    private String editedDate;

    @Column(name = "last_edited_by")
    private String lastEditedBy;

    @Column(name = "created_by")
    private String createdBy;
}
