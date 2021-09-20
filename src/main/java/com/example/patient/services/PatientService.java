package com.example.patient.services;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.VilleRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository pr;

    public PatientService(PatientRepository pr, VilleRepository vr) {
        this.pr = pr;
    }

    public List<PatientEntity> getAllPatient() {
        return (List<PatientEntity>) pr.findAll();

    }

    public Optional<PatientEntity> getPatientById( int id) {

        return pr.findById(id);
    }
    
    public PatientEntity findByEmail( String email) {

        return pr.findByEmail(email);
    }

    private void  checkPatient( String nom, String prenom, String telephone , String email ) throws Exception {
        if( prenom.length() < 2 ){
            throw new Exception("Invalid value pour prénom");
        }

        if( nom.length() < 2 ){
            throw new Exception("Invalid value pour nom");
        }

        if( telephone.length() < 2 ){
            throw new Exception("Invalid value pour telephone");
        }

        if( email.length() < 2 ){
            throw new Exception("Invalid value pour email");
        }
    }

    public PatientEntity addPatient(String nom, String prenom, String email, String telephone, int ville ) throws Exception {
            checkPatient(nom, prenom, email, telephone);
            PatientEntity p = new PatientEntity();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setEmail(email);
            p.setTelephone(telephone);
            VilleEntity villeP = new VilleEntity();
            villeP.setId(ville);
            p.setVille(villeP);
            pr.save(p);
            return p;

    }

    public Optional<PatientEntity> updatePatient(int id, String nom, String prenom, String email, String telephone, int ville) {
        try {
            Optional<PatientEntity> p = pr.findById(id);
            p.get().setNom(nom);
            p.get().setPrenom(prenom);
            p.get().setEmail(email);
            p.get().setTelephone(telephone);
            VilleEntity villeP = new VilleEntity();
            villeP.setId(ville);
            p.get().setVille(villeP);
            pr.save(p.get());
            return p;
        } catch (Exception e){
            System.out.println("Erreur :" + e);
            return null;
        }
    }

    public void deletePatient(int id) {
        Optional<PatientEntity> p = this.getPatientById(id);
        try {
            pr.delete(p.get());
        } catch (Exception e) {
            System.out.println("Erreur" + e);
        }
    }
   
}
