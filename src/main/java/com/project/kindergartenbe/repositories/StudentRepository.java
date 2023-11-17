package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.StudentBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentBE, Long> {

}
