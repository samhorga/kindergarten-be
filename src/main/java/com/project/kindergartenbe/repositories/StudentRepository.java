package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.StudentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentBE, Long> {

    @Query(value = "SELECT s FROM students s JOIN adult_student a WHERE a.adult_id = :adultId", nativeQuery = true)
    List<StudentBE> findStudentsByAdultId(Long adultId);
}
