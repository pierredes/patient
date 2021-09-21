package com.example.patient.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.patient.entities.RendezvousEntity;


public interface RendezvousRepository extends CrudRepository<RendezvousEntity, Integer> {
}
