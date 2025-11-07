package com.capgemini.Springsrinjoy.controller;



import com.capgemini.Springsrinjoy.entity.Patient;
import com.capgemini.Springsrinjoy.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService service;
    @PostMapping("/add")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePatient(patient));
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Patient>> getAllSorted(@RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(service.getAllPatientSorted(sortBy));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = service.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @Valid @RequestBody Patient patient) {
        Patient updated = service.updatePatient(id, patient);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    // Filters and JPQL
    @GetMapping("/disease/{disease}")
    public ResponseEntity<List<Patient>> findByDisease(@PathVariable String disease) {
        return ResponseEntity.ok(service.getByDisease(disease));
    }
    @GetMapping("/ageGreaterThan/{age}")
    public ResponseEntity<List<Patient>> findByAgeGreater(@PathVariable int age) {
        return ResponseEntity.ok(service.getByAgeGreaterThan(age));
    }
    @GetMapping("/billLessThan/{amount}")
    public ResponseEntity<List<Patient>> findByBillLess(@PathVariable double amount) {
        return ResponseEntity.ok(service.getByBillAmountLessThan(amount));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchByName(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchByName(keyword));
    }
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Patient>> findByGenderNative(@PathVariable String gender) {
        return ResponseEntity.ok(service.getByGenderNative(gender));
    }
    @PutMapping("/updateBill/{id}/{amount}")
    public ResponseEntity<String> updateBill(@PathVariable Long id, @PathVariable double amount) {
        int updated = service.updateBillAmount(id, amount);
        return ResponseEntity.ok(updated + " record(s) updated");
    }
    @DeleteMapping("/deleteByAgeLessThan/{age}")
    public ResponseEntity<String> deleteByAgeLess(@PathVariable int age) {
        int deleted = service.deleteByAgeLessThan(age);
        return ResponseEntity.ok(deleted + " record(s) deleted");
    }
}