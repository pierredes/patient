package com.example.patient.services;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    private final VilleRepository vr;

    public VilleService( VilleRepository vr) {

        this.vr = vr;
    }

    public List<VilleEntity> getAllVille() {
        return (List<VilleEntity>) vr.findAll();

    }

    public Optional<VilleEntity> getVilleById(String id) {
        return vr.findById(Integer.parseInt(id));
    }

    public VilleEntity addVille( int cp, String nom ) {
        try {
            VilleEntity v = new VilleEntity();
            v.setCodePostal(cp);
            v.setNom(nom);
            vr.save(v);
            return v;
        } catch (Exception e) {
            System.out.println("Erreur" + e);
            return null;
        }
    }

    public Optional<VilleEntity> updateVille(String id, String nom, int cp) {
        Optional<VilleEntity> v = this.getVilleById(id);
        try {
            v.get().setNom(nom);
            v.get().setCodePostal(cp);
            vr.save(v.get());
            return v;
        } catch (Exception e) {
            System.out.println("Erreur :" + e);
            return null;
        }
    }

    public void deleteVille(String id) {
        Optional<VilleEntity> v = this.getVilleById(id);
        try {
            vr.delete(v.get());
        } catch (Exception e) {
            System.out.println("Erreur" + e);
        }
    }
}
