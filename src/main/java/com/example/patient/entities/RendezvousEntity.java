package com.example.patient.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rendezvous", schema = "medical", catalog = "")
public class RendezvousEntity {
	private int id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    private String type;
    private int duree;
    private String note;
    private PatientEntity patient;
    
    
    @Id
    @Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Basic
    @Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Basic
    @Column(name = "type")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Basic
    @Column(name = "duree")
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	@Basic
    @Column(name = "note")
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

	    
    @OneToOne
    @JoinColumn(name = "patient", referencedColumnName = "id")
	public PatientEntity getPatient() {
		return patient;
	}
	
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	

  
}
