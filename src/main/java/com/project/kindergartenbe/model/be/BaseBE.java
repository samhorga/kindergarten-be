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
    public String createdDate;

    @Column(name = "edited_date")
    public String editedDate;

    @Column(name = "last_edited_by")
    public String lastEditedBy;

    @Column(name = "created_by")
    public String createdBy;
}
