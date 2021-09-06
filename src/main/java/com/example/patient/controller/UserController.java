package com.example.patient.controller;

import com.example.patient.entities.UserEntity;
import com.example.patient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/user")
public class UserController {

    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }


    @GetMapping("/list")
    public String getAllUser (Model model) {
        model.addAttribute("message", "Voici la liste des utilisateurs");
        List<UserEntity> ul = us.getAllUser();
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

        us.addUser(username, email, role, password, nom);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("message", "Modifier un utilisateur");
        Optional<UserEntity> u = us.getUserById(id);
        model.addAttribute("user", u.get());
        return "/user/add_edit.html";
    }

    @PostMapping("/edit/{id}")
    public String postEditUser(@PathVariable(name = "id") String id, HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String nom = request.getParameter("nom");

        us.updateUser(id, username, email, role, nom);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        us.deleteUser(id);
        return "redirect:/user/list";
    }
}
