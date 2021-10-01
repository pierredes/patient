package com.example.patient.controller;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.UserRepository;
import com.example.patient.repositories.VilleRepository;
import com.example.patient.services.PatientService;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService ps;
    private final VilleService vs;

    public PatientController(PatientService ps, VilleService vs) {
        this.ps = ps;
        this.vs = vs;
    }
    
    @Autowired
    private PatientRepository pr;

    // Get all
    @GetMapping("/list")
    public String getAllPatient(Model model) {
        model.addAttribute("message", "Voila la lite des patient");
        //List<PatientEntity> lp = ps.getAllPatient();
        List<VilleEntity> lv = vs.getAllVille();
        model.addAttribute("lv", lv);
       // model.addAttribute("lp", lp);

        return "patient/list";
    }

    // get data for add form
    @GetMapping("/add")
    public String addGet(Model model) {
        model.addAttribute("message", "Ajouter un patient");
        List<VilleEntity> lv = vs.getAllVille();
        model.addAttribute("lv", lv);
        return "patient/add_edit";
    }

    // post data for add form
    @PostMapping("/add")
    public String addPatient(HttpServletRequest request, Model model) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String ville = request.getParameter("ville");
        try {
            ps.addPatient(nom, prenom, email, telephone, Integer.parseInt(ville));
            return "redirect:/patient/list?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage() );
            return "redirect:/patient/list?error";
        }
    }

    // get data for edit form
    @GetMapping("/edit/{id}")
    public String getEditPatient(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("message", "Modifier un patient");
        Optional<PatientEntity> p = ps.getPatientById(id);
        List<VilleEntity> lv = vs.getAllVille();
        if (p.get().getVille() == null) {
            model.addAttribute("patient", p.get());
            model.addAttribute("lv", lv);
        }
        else {
            Optional<VilleEntity> v = vs.getVilleById(Integer.toString(p.get().getVille().getId()));
            List<VilleEntity> lv2 = vs.getAllVille();
            for (VilleEntity ville : lv) {
                model.addAttribute("patient", p.get());
                if (ville.getId() == v.get().getId()) {
                    lv2.remove(ville);
                    model.addAttribute("ville1", v.get());
                }
                model.addAttribute("lv", lv2);
            }
        }
        return "patient/add_edit";
    }

    // post data for edit form
    @PostMapping("/edit/{id}")
    public String postEditPatient(@PathVariable(name = "id") int id, HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String ville = request.getParameter("ville");


        ps.updatePatient(id, nom, prenom, email, telephone, Integer.parseInt(ville));
        return "redirect:/patient/list";
    }

    // delete data
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") int id, Model model) {
        ps.deletePatient(id);

        return "redirect:/patient/list";
    }
    
    @GetMapping("/check")
    public String checkPatient(@RequestParam String email) throws Exception{
    	
    	try {
    		PatientEntity ps1 = ps.findByEmail(email);
    		ps1.getEmail();
    		return "/patient/list";
    	} catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "erreur" );
    	}
    	
    }
}
