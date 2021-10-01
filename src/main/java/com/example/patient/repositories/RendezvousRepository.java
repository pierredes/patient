package com.example.patient.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.patient.entities.RendezvousEntity;


public interface RendezvousRepository extends CrudRepository<RendezvousEntity, Integer> {
	public List<RendezvousEntity> findByPatientNomContains(String search);
}
