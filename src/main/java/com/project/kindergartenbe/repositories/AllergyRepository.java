package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.NoteBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<AllergyBE, Long> {
}
