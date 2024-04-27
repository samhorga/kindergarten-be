package com.project.kindergartenbe.model.be;

import com.project.kindergartenbe.model.dos.GroupDO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "groups") // Define the table name
public class GroupBE extends BaseBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolBE school;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<StudentBE> students;

    public GroupBE(GroupDO groupDO) {
        this.id = groupDO.getId();
        this.name = groupDO.getName();
        if (Objects.nonNull(groupDO.getSchoolDO())) {
            this.school = new SchoolBE(groupDO.getSchoolDO());
        }
        this.students = groupDO.getStudents().stream().map(StudentBE::new).collect(Collectors.toList());
        this.createdDate = Objects.nonNull(groupDO.getCreatedDate()) ? groupDO.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(groupDO.getEditedDate()) ? groupDO.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(groupDO.getLastEditedBy()) ? groupDO.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(groupDO.getCreatedBy()) ? groupDO.getCreatedBy() : null;
    }
}

