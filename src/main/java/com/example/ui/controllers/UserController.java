package com.example.ui.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.exeptions.UserExistsException;
import com.example.exeptions.UserNameNotFoundException;
import com.example.exeptions.UserNotFoundException;
import com.example.services.UserService;
import com.example.ui.models.UserModel;

@RestController
@RequestMapping("/users")
@Validated
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
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel user, UriComponentsBuilder uriBuilder)
			throws Exception {
		try {
			UserModel createdUser;
			createdUser = userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<>(createdUser, headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Optional<UserModel> getUserById(@PathVariable @Min(1) int id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@PutMapping("/{id}")
	public UserModel updateUser(@RequestBody UserModel user, @PathVariable int id) throws Exception {
		try {
			UserModel updatedUser = userService.updateUser(user, id);
			return updatedUser;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);

	}

	@GetMapping("/byusername/{username}")
	public UserModel getUserById(@PathVariable String username) throws Exception {
		UserModel foundUser = userService.getUserByUsername(username);
		if (foundUser == null) {
			throw new UserNameNotFoundException("User with given username does not exists");
		}
		return foundUser;

	}
}
