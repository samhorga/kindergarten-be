package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.AllergyBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdultRepository extends JpaRepository<AdultBE, Long> {
}
