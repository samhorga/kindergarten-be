package com.project.kindergartenbe.model.dos;

import com.project.kindergartenbe.model.be.GroupBE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GroupDO extends BaseDO {
    private Long id;
    private String name;
    private List<StudentDO> students;
    private SchoolDO schoolDO;

    public GroupDO(GroupBE groupBE) {
        this.id = groupBE.getId();
        this.name = groupBE.getName();
        if (Objects.nonNull(groupBE.getSchool())) {
            this.schoolDO = new SchoolDO(groupBE.getSchool());
        }
        this.createdDate = Objects.nonNull(groupBE.getCreatedDate()) ? groupBE.getCreatedDate() : null;
        this.editedDate = Objects.nonNull(groupBE.getEditedDate()) ? groupBE.getEditedDate() : null;
        this.lastEditedBy = Objects.nonNull(groupBE.getLastEditedBy()) ? groupBE.getLastEditedBy() : null;
        this.createdBy = Objects.nonNull(groupBE.getCreatedBy()) ? groupBE.getCreatedBy() : null;
    }
}
