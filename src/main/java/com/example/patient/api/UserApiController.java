package com.example.patient.api;

import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.services.UserService;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ws/user")
public class UserApiController {
    @Autowired
    UserService us;

    @GetMapping(path = "/", produces = "application/json")
    List<UserEntity> getAllUserApi() {
        return us.getAllUser();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    Optional<UserEntity> getUserByIdApi(@PathVariable(name = "id") String id) {
        return us.getUserById(id);
    }

    @PostMapping(path = "/add", produces = "application/json")
    UserEntity addUserApi(@RequestBody UserEntity user) {
        return us.addUser(user.getUsername(), user.getEmail(), user.getRoles(), user.getPassword(), user.getName());
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    Optional<UserEntity> updateUserApi(@PathVariable(name = "id") String id, @RequestBody UserEntity user ) {
        return us.updateUser(id, user.getUsername(), user.getEmail(), user.getRoles(), user.getPassword(), user.getName());
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteVille(@PathVariable(name = "id") String id) {
        us.deleteUser(id);
    }
}
