package com.capgemini.Springsrinjoy.service.impl;
 
import com.capgemini.Springsrinjoy.entity.Patient;
import com.capgemini.Springsrinjoy.exception.PatientNotFoundException;
import com.capgemini.Springsrinjoy.repository.PatientRepository;
import com.capgemini.Springsrinjoy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
 
@Service
public  class PatientServiceImpl implements PatientService {
 
    @Autowired
    private PatientRepository repo;
 
    @Override
    public Patient savePatient(Patient patient) {
        return repo.save(patient);
    }
 
    
 
    @Override
    public void deletePatient(Long id) {
        if(!repo.existsById(id)){
            throw new PatientNotFoundException("Can't Delete. Patient not found with ID: "+ id);
        }
        repo.deleteById(id);
    }
 
    
 
    @Override
    public Patient getPatientById(Long id) {
        return repo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with Id: + id"));
    }
 
    @Override
    public List<Patient> getByDisease(String disease) {
        return repo.findByDisease(disease);
    }
 
    @Override
    public List<Patient> getByAgeGreaterThan(int age) {
        return repo.findByAgeGreaterThan(age);
    }
 
    @Override
    public List<Patient> getByBillAmountLessThan(double amount) {
        return repo.findByBillAmountLessThan(amount);
    }
 
    @Override
    public List<Patient> searchByName(String keyword) {
        return repo.searchByName(keyword);
    }
 
    @Override
    public List<Patient> getByGenderNative(String gender) {
        return repo.findByGenderNative(gender);
    }
 
    @Override
    @Transactional
    public int updateBillAmount(Long id, double amount) {
        return repo.updateBillAmount(id, amount);
    }
 
    @Override
    @Transactional
    public int deleteByAgeLessThan(int age) {
        return repo.deleteByAgeLessThan(age);
    }

	@Override
	public Patient updatePatient(Long id, Patient updated) {
		Patient existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setAge(updated.getAge());
            existing.setGender(updated.getGender());
            existing.setDisease(updated.getDisease());
            existing.setBillAmount(updated.getBillAmount());
            return repo.save(existing);
        }
        return null;
	}

	@Override
	public List<Patient> getAllPatientSorted(String sortBy) {
		return repo.findAll(Sort.by(Sort.Direction.ASC, sortBy));
	}
}
 