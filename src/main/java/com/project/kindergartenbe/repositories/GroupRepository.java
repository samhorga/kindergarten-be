package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.GroupBE;
import com.project.kindergartenbe.model.be.SchoolBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupBE, Long> {
}
