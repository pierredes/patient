package com.example.patient.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.RendezvousEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.services.PatientService;
import com.example.patient.services.RendezvousService;

@Controller
@RequestMapping("/rdv")
public class RendezvousController {
	private final RendezvousService rs;
	private final PatientService ps;
	
	public RendezvousController(RendezvousService rs, PatientService ps) {
		this.rs = rs;
		this.ps = ps;
	}
	
	@GetMapping("/list")
	public String getAllRdv(Model model) {
		List<RendezvousEntity> lr = rs.getAllRdv();
		List<PatientEntity> lp = ps.getAllPatient();
		model.addAttribute("lr", lr);
		model.addAttribute("lp", lp);
		return "/rendezvous/list";
	}
	
	// get data for add form
    @GetMapping("/add")
    public String addGetRdv(Model model) {
        model.addAttribute("message", "Ajouter un rendez vous");
        List<PatientEntity> lp = ps.getAllPatient();
        model.addAttribute("lp", lp);
        return "patient/add_edit";
    }
	
	@PostMapping("/add")
	public String addRdv(HttpServletRequest request) {
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		String duree = request.getParameter("duree");
		String note = request.getParameter("note");
		String patient = request.getParameter("patient");
		System.out.print(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date_convertie = formatter.parse(date);
			System.out.println(date_convertie);
			rs.addRdv(date_convertie, type, Integer.parseInt(duree), note, Integer.parseInt(patient));
			return "redirect:/rdv/list?success";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/rdv/list?error";
		}
	}
	
	@PostMapping("/edit/{id}")
	public String updateRdv(@PathVariable(name = "id") int id, HttpServletRequest request) {
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		String duree = request.getParameter("duree");
		String note = request.getParameter("note");
		String patient = request.getParameter("patient");
		System.out.print(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			Date date_convertie = formatter.parse(date);
			System.out.println(date_convertie);
			rs.updateRdv(id, date_convertie, type, Integer.parseInt(duree), note, Integer.parseInt(patient));
			return "redirect:rdv/list?success";
		} catch (Exception e) {
			return "redirect:rdv/list?error";
		}
	}
	
	@GetMapping("/delete/{id}")
	@ResponseBody
	public String deleteRdv(@PathVariable(name = "id") int id) {
		try{
            rs.deleteRdv(id);
        	System.out.println("Supprimer " + id);
        }catch( Exception e ){
        	throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
        return ""; 
	}
}
