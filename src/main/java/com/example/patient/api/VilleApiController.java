package com.example.patient.api;

import com.example.patient.entities.VilleEntity;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ws/ville")
@CrossOrigin("http://localhost:4200")
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

    @PostMapping(path = "/add", produces = "application/json")
    VilleEntity addVilleApi(@RequestBody VilleEntity ville) {
        return vs.addVille(ville.getCodePostal(), ville.getNom());
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
