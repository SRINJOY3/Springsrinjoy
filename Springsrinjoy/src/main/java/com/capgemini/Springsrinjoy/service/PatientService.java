package com.capgemini.Springsrinjoy.service;

import com.capgemini.Springsrinjoy.entity.Patient;
import java.util.List;
public interface PatientService {
    Patient savePatient(Patient patient);
    Patient updatePatient(Long id, Patient updated);
    void deletePatient(Long id);
    Patient getPatientById(Long id);
    List<Patient> getAllPatientSorted(String sortBy);
    List<Patient> getByDisease(String disease);
    List<Patient> getByAgeGreaterThan(int age);
    List<Patient> getByBillAmountLessThan(double amount);
    List<Patient> searchByName(String keyword);
    List<Patient> getByGenderNative(String gender);
    int updateBillAmount(Long id, double amount);
    int deleteByAgeLessThan(int age);
}
