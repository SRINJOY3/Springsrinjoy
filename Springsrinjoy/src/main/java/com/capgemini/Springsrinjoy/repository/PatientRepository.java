package com.capgemini.Springsrinjoy.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import com.capgemini.Springsrinjoy.entity.Patient;
import java.util.List;
@RepositoryRestResource(path = "patients", collectionResourceRel = "patients")
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @RestResource(exported = false)
    List<Patient> findByDisease(String disease);
    List<Patient> findByAgeGreaterThan(int age);
    List<Patient> findByBillAmountLessThan(double amount);

    @Query("SELECT p FROM Patient p WHERE p.name LIKE %:name%")
    List<Patient> searchByName(@Param("name") String name);

    @Modifying
    @Query("UPDATE Patient p SET p.billAmount = :amount WHERE p.id = :id")
    int updateBillAmount(@Param("id") Long id, @Param("amount") double amount);

    @Modifying
    @Query("DELETE FROM Patient p WHERE p.age < :age")
    int deleteByAgeLessThan(@Param("age") int age);

    @Query(value = "SELECT * FROM patients WHERE gender = :gender", nativeQuery = true)
    List<Patient> findByGenderNative(@Param("gender") String gender);
}