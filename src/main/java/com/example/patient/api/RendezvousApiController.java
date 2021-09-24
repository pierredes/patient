package com.example.patient.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.RendezvousEntity;
import com.example.patient.services.RendezvousService;

@RestController()
@RequestMapping("/ws/rdv")
public class RendezvousApiController {
	
	@Autowired
    RendezvousService rs;
	
	@GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<RendezvousEntity> getPatientByIdApi(@PathVariable(name = "id") int id) {
        RendezvousEntity rendezvousGet = rs.getRdvById(id);
        if (rendezvousGet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rendezvousGet);
        }
    }

}
