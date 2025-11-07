package com.capgemini.Springsrinjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.Springsrinjoy.entity.Doctor;
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}