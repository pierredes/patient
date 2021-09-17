package com.example.patient.services;

import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUser() {
        return (List<UserEntity>) ur.findAll();

    }

    public Optional<UserEntity> getUserById(String id) {
        return ur.findById(Integer.parseInt(id));
    }

    public UserEntity addUser( String username, String email, String role, String password, String nom ) {
        try {
            UserEntity u = new UserEntity();
            u.setUsername(username);
            u.setEmail(email);
            u.setRoles(role);
            u.setPassword(passwordEncoder.encode(password));
            u.setName(nom);
            ur.save(u);
            return u;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public Optional<UserEntity> updateUser(String id, String username, String email, String role, String password, String nom) {
        try {
            Optional<UserEntity> u = this.getUserById(id);

            u.get().setUsername(username);
            u.get().setEmail(email);
            u.get().setRoles(role);
            if (password != "") {
                u.get().setPassword(passwordEncoder.encode(password));
            }
            u.get().setName(nom);
            ur.save(u.get());
            return u;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "erreur" );
        }
    }

    public void deleteUser(String id) {
        Optional<UserEntity> u = this.getUserById(id);
        try {
            ur.delete(u.get());
        } catch (Exception e) {
            System.out.println("Erreur" + e);
        }
    }
}
