package com.example.patient.api;

import com.example.patient.entities.PatientEntity;
import com.example.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController()
@RequestMapping("/ws/patient")
public class PatientApiController {

    @Autowired
    PatientService ps;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<PatientEntity>> getallPatientApi() {

        try {
            return ResponseEntity.ok().body(ps.getAllPatient());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Optional<PatientEntity>> getPatientByIdApi(@PathVariable(name = "id") int id) {
        Optional<PatientEntity> patientGet = ps.getPatientById(id);
        if (!patientGet.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(patientGet);
        }
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<PatientEntity> addPatientApi(@RequestBody PatientEntity patient) {
        try{
            PatientEntity createPatient = ps.addPatient( patient.getNom() , patient.getPrenom(), patient.getEmail() , patient.getTelephone() , patient.getVille().getId() );

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createPatient.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createPatient);

        }catch ( Exception e ){
            System.out.println("Je suis ici");
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }


    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<Optional<PatientEntity>> updatePatientApi(@PathVariable(name = "id") int id, @RequestBody PatientEntity patient) {
        Optional<PatientEntity> patientUpdate = ps.updatePatient(id, patient.getNom(), patient.getPrenom(), patient.getEmail(), patient.getTelephone(), patient.getVille().getId());
        if (patientUpdate == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI uir = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientUpdate).toUri();
            return ResponseEntity.created(uir).body(patientUpdate);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable(name = "id")int id) {
        try {
            ps.deletePatient(id);
            return ResponseEntity.ok()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
