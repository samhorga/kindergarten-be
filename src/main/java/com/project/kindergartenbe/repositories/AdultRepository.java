package com.project.kindergartenbe.repositories;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.AllergyBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdultRepository extends JpaRepository<AdultBE, Long> {
    @Query("SELECT a FROM AdultBE a WHERE" +
            " LOWER(a.firstName) = LOWER(:firstName) AND " +
            "LOWER(a.lastName) = LOWER(:lastName) AND " +
            "LOWER(a.relationship) = LOWER (:relationship) AND " +
            "LOWER(a.phoneNumber) = LOWER(:phoneNumber)")
    Optional<List<AdultBE>> tryToFindAdultByFirstNameLastName(String firstName, String lastName, String relationship, String phoneNumber);
}
