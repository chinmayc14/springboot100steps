package com.example.ui.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.UserService;
import com.example.ui.models.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {

	UserService userService;

	@Autowired
	UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
		UserModel createdUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@GetMapping("/{id}")
	public UserModel getUserById(@PathVariable int id) {
		Optional<UserModel> checkNull = userService.getUserById(id);
		return checkNull.orElseGet(UserModel::new);
	}

	@PutMapping("/{id}")
	public UserModel updateUser(@RequestBody UserModel user) {
		UserModel updatedUser = userService.updateUser(user);
		return updatedUser;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);

	}

	@GetMapping("/byusername/{username}")
	public UserModel getUserById(@PathVariable String username) {
		return userService.getUserByUsername(username);

	}
}
