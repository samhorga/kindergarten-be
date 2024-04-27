package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.SchoolBE;
import com.project.kindergartenbe.model.be.VaccineBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolBE, Long> {
}
