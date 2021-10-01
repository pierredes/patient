package com.example.patient.repositories;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.UserEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Integer> {
	public PatientEntity findByEmail(String email);

	public List<PatientEntity> findByNomContainsOrPrenomContains(String search, String search2);
}
