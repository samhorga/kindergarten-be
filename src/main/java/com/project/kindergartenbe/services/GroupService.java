package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.GroupBE;
import com.project.kindergartenbe.model.be.SchoolBE;
import com.project.kindergartenbe.model.dos.GroupDO;
import com.project.kindergartenbe.model.dos.SchoolDO;
import com.project.kindergartenbe.repositories.GroupRepository;
import com.project.kindergartenbe.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private SchoolRepository schoolRepository;

    public GroupService(GroupRepository groupRepository, SchoolRepository schoolRepository) {
        this.groupRepository = groupRepository;
        this.schoolRepository = schoolRepository;
    }

    // Method to create a new school
    public GroupDO createGroup(Long schoolId, GroupDO groupDO) {
        GroupBE groupBE = new GroupBE(groupDO);
        Optional<SchoolBE> schoolBEOpt = schoolRepository.findById(schoolId);
        schoolBEOpt.ifPresent(groupBE::setSchool);
        groupBE.setCreatedBy("SAM");
        groupBE.setCreatedDate(LocalDateTime.now().toString());
        groupBE.setLastEditedBy("SAM");
        groupBE.setEditedDate(LocalDateTime.now().toString());
        GroupBE createdGroup = groupRepository.save(groupBE);
        return new GroupDO(createdGroup);
    }

    // Method to retrieve all schools
    public List<GroupDO> getAllGroups() {
        List<GroupBE> groupBEs = groupRepository.findAll();
        return groupBEs.stream().map(GroupDO::new).collect(Collectors.toList());
    }

    //
//    // Method to retrieve a specific school by ID
//    public SchoolDO getSchoolById(Long id) {
//        Optional<School> optionalSchool = schoolRepository.findById(id);
//        return optionalSchool.orElse(null);
//    }
//
    // Method to update an existing school
    public GroupDO updateGroup(Long id, GroupDO updatedGroup) {
        Optional<GroupBE> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            GroupBE existingGroup = optionalGroup.get();
            existingGroup.setEditedDate(LocalDateTime.now().toString());
            existingGroup.setLastEditedBy("SAM");
            existingGroup.setName(updatedGroup.getName());
            // Update existingSchool with values from updatedSchool
            // Save and return the updatedSchool
            return new GroupDO(groupRepository.save(existingGroup));
        }
        return null;
    }

    // Method to delete a school by ID
    public boolean deleteGroup(Long id) {
        Optional<GroupBE> optionalSchool = groupRepository.findById(id);
        if (optionalSchool.isPresent()) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
