package com.example.patient.controller;

import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.VilleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ville")
public class VilleController {
    private final VilleRepository vr;

    public VilleController(VilleRepository vr) {
        this.vr = vr;
    }

    @GetMapping("/list")
    public String getVille(Model model) {
    model.addAttribute("message", "Voici la liste des villes");
        List<VilleEntity> lv = (List<VilleEntity>) vr.findAll();
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
        try {
            VilleEntity v = new VilleEntity();
            v.setCodePostal(Integer.parseInt(cp));
            v.setNom(nom);
            vr.save(v);
        } catch (Exception e) {
            System.out.println("Erreur" + e);
        }
        return "redirect:/ville/list";
    }

    @GetMapping("/edit/{id}")
    public String GetEditVille(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("message", "Modifier une ville");
        Optional<VilleEntity> v = vr.findById(id);
        model.addAttribute("ville", v.get());
        return "/ville/add_edit.html";
    }

    @PostMapping("/edit/{id}")
    public String postEditVille(@PathVariable(name = "id") int id, HttpServletRequest request) {
        Optional<VilleEntity> v = vr.findById(id);
        String nom = request.getParameter("nom");
        String cp = request.getParameter("cp");
        try {
            v.get().setNom(nom);
            v.get().setCodePostal(Integer.parseInt(cp));
            vr.save(v.get());
        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }
        return "redirect:/ville/list";
    }

    @GetMapping("/delete/{id}")
    public String postEditVille(@PathVariable(name = "id") int id) {
        Optional<VilleEntity> v = vr.findById(id);
        try {
            vr.delete(v.get());
        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }
        return "redirect:/ville/list";
    }

}
