package com.capgemini.Springsrinjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.Springsrinjoy.entity.Doctor;
import com.capgemini.Springsrinjoy.entity.Patient;
import com.capgemini.Springsrinjoy.repository.DoctorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired private DoctorRepository doctorRepo;

    @PostMapping ("/add")
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorRepo.save(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<List<Patient>> getPatients(@PathVariable Long id){
        Doctor doc = doctorRepo.findById(id).orElse(null);
        return (doc != null) ? ResponseEntity.ok(doc.getPatients()) : ResponseEntity.notFound().build();
    }
}