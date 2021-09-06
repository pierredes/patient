package com.example.patient.controller;

import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository ur;

    public UserController(UserRepository ur) {
        this.ur = ur;
    }

    @GetMapping("/list")
    public String getAllUser (Model model) {
        model.addAttribute("message", "Voici la liste des utilisateurs");
        List<UserEntity> ul = (List<UserEntity>) ur.findAll();
        model.addAttribute("ul", ul);
        return "/user/list";
    }

    @GetMapping("/add")
    public String getAddUser(Model model) {
        model.addAttribute("message", "Ajouter un utilisateur");
        return "/user/add_edit";
    }

    @PostMapping("/add")
    public String postAddUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");

        try {
            UserEntity u = new UserEntity();
            u.setUsername(username);
            u.setEmail(email);
            u.setRoles(role);
            u.setPassword(passwordEncoder.encode(password));
            u.setName(nom);
            ur.save(u);
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("message", "Modifier un utilisateur");
        Optional<UserEntity> u = ur.findById(id);
        model.addAttribute("user", u.get());
        return "/user/add_edit.html";
    }

    @PostMapping("/edit/{id}")
    public String postEditUser(@PathVariable(name = "id") int id, HttpServletRequest request) {
        Optional<UserEntity> u = ur.findById(id);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String nom = request.getParameter("nom");

        try {
            u.get().setUsername(username);
            u.get().setEmail(email);
            u.get().setRoles(role);
            u.get().setName(nom);
            ur.save(u.get());
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        Optional<UserEntity> u = ur.findById(id);
        ur.delete(u.get());
        return "redirect:/user/list";
    }
}
