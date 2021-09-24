package com.example.patient.api;

import com.example.patient.entities.VilleEntity;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/ws/ville")

public class VilleApiController {
    @Autowired
    VilleService vs;

    @GetMapping(path = "/", produces = "application/json")
    List<VilleEntity> getAllVilleApi() {
        return vs.getAllVille();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    Optional<VilleEntity> getVilleByIdApi(@PathVariable(name = "id") String id) {
        return vs.getVilleById(id);
    }

    @PostMapping(path = "/", produces = "application/json")
    ResponseEntity<VilleEntity> addVilleApi(@RequestBody VilleEntity ville) {
        try{
            VilleEntity createVille = vs.addVille(ville.getCodePostal() , ville.getNom() ); 

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createVille.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createVille);

        }catch ( Exception e ){
            System.out.println("Je suis ici");
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }
    @PutMapping(path = "/update/{id}", produces = "application/json")
    Optional<VilleEntity> updateVilleApi(@PathVariable(name = "id") String id, @RequestBody VilleEntity ville ) {
        return vs.updateVille(id, ville.getNom(), ville.getCodePostal());
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteVille(@PathVariable(name = "id") String id) {
        vs.deleteVille(id);
    }
}
