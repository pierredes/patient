package com.example.patient.api;

import com.example.patient.entities.UserEntity;
import com.example.patient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/ws/login")
public class loginApiController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path = "/", produces = "application/json")
	ResponseEntity<UserEntity>checkLogin( @RequestBody UserEntity userv) {
		try {
			UserEntity user = userRepository.findByEmailOrUsername(userv.getEmail(), userv.getUsername());
			user.setPassword("");
			return ResponseEntity.ok()
					.body(user);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}

}
