package com.example.patient.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.RendezvousEntity;
import com.example.patient.repositories.RendezvousRepository;

@Service
public class RendezvousService {
	private final RendezvousRepository rp;
	
	public RendezvousService(RendezvousRepository rp) {
		this.rp = rp;
	}

	public List<RendezvousEntity> getAllRdv() {
		return (List<RendezvousEntity>) rp.findAll();
	}
	
	public RendezvousEntity getRdvById(int id) {
		return rp.findById(id).get();
	}
	
	public RendezvousEntity addRdv(Date date, String type, int duree, String note, int patient ) throws ParseException {
		RendezvousEntity rdv = new RendezvousEntity();
	
        
       
        
		rdv.setDate(date);
		rdv.setType(type);
		rdv.setDuree(duree);
		rdv.setNote(note);
		PatientEntity p = new PatientEntity();
		p.setId(patient);
		rdv.setPatient(p);
		rp.save(rdv);
		return rdv;
	}
	
	public RendezvousEntity updateRdv(int id, Date date, String type, int duree, String note, int patient) throws ParseException {
		RendezvousEntity rdv = this.getRdvById(id);
		
        
		rdv.setDate(date);
		rdv.setType(type);
		rdv.setDuree(duree);
		rdv.setNote(note);
		PatientEntity p = new PatientEntity();
		p.setId(patient);
		rdv.setPatient(p);
		rp.save(rdv);
		return rdv;
	}
	
	public void deleteRdv(int id) {
		RendezvousEntity rdv = this.getRdvById(id);
		rp.delete(rdv);
	}
}
