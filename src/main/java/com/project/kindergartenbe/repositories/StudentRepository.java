package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.StudentBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentBE, Long> {

    @Query("SELECT s FROM StudentBE s JOIN s.adults a WHERE a.id = :adultId")
    List<StudentBE> findStudentsByAdultId(@Param("adultId") Long adultId);
}
