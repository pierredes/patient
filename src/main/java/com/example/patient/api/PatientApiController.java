package com.example.patient.api;

import com.example.patient.entities.PatientEntity;
import com.example.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/ws/patient")
public class PatientApiController {

    @Autowired
    PatientService ps;

    @GetMapping(path = "/", produces = "application/json")
    List<PatientEntity> getallPatientApi() {
        return (List<PatientEntity>) ps.getAllPatient();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    Optional<PatientEntity> getPatientByIdApi(@PathVariable(name = "id") int id) {
        return ps.getPatientById(id);
    }

    @PostMapping(path = "/add", produces = "application/json")
    PatientEntity addPatientApi(@RequestBody PatientEntity patient) {
        return ps.addPatient(patient.getNom(), patient.getPrenom(), patient.getEmail(), patient.getTelephone(), patient.getVille().getId());
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    Optional<PatientEntity> updatePatientApi(@PathVariable(name = "id") int id, @RequestBody PatientEntity patient) {
        return ps.updatePatient(id, patient.getNom(), patient.getPrenom(), patient.getEmail(), patient.getTelephone(), patient.getVille().getId());
    }

    @DeleteMapping(path = "/delete/{id}")
    void deletePatient(@PathVariable(name = "id")int id) {
        ps.deletePatient(id);
    }


}
