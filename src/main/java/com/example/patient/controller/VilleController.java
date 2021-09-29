package com.example.patient.controller;

import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.VilleRepository;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ville")
public class VilleController {

    private final VilleService vs;

    public VilleController(VilleService vs) {
        this.vs = vs;
    }

    @GetMapping("/list")
    public String getVille(Model model) {
        model.addAttribute("message", "Voici la liste des villes");
        List<VilleEntity> lv = vs.getAllVille();
        model.addAttribute("lv", lv);
        return "/ville/list";
    }

    @GetMapping("/add")
    public String addGetVille (Model model) {
        model.addAttribute("message", "Ajouter une ville");
        return "/ville/add_edit";
    }

    @PostMapping("/add")
    public String addPostVille (HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String cp = request.getParameter("cp");
        vs.addVille(Integer.parseInt(cp), nom);
        return "redirect:/ville/list";
    }

    @GetMapping("/edit/{id}")
    public String GetEditVille(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("message", "Modifier une ville");
        Optional<VilleEntity> v = vs.getVilleById(id);
        model.addAttribute("ville", v.get());
        return "/ville/add_edit.html";
    }

    @PostMapping("/edit/{id}")
    public String postEditVille(@PathVariable(name = "id") String id, HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String cp = request.getParameter("cp");
        vs.updateVille(id, nom, Integer.parseInt(cp));
        return "redirect:/ville/list";
    }

    @GetMapping("/delete/{id}")
    public String postEditVille(@PathVariable(name = "id") int id) {
        vs.deleteVille(id);
        return "redirect:/ville/list";
    }

}
