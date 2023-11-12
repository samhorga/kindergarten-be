package com.project.kindergartenbe.model.be;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notes") // Define the table name
@Getter
@Setter
public class NoteBE extends BaseBE {

    @Column(name = "note_text")
    private String note;
}
