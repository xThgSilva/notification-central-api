package com.notification.central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.central.dto.UserDTO;
import com.notification.central.entities.User;
import com.notification.central.services.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> createAccount(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.createAccount(dto));
	}
	
	@GetMapping(value ="/all")
	public ResponseEntity<List<User>> listAllUsers() {
		return ResponseEntity.ok(userService.listAllUsers());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findUserById(id));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUserById(id));
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.updateUserById(id, dto));
	}
}	