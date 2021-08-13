package com.example.ui.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.exeptions.UserNotFoundException;
import com.example.services.UserService;
import com.example.ui.models.OrderModel;
import com.example.ui.models.UserModel;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<UserModel> getAllUsers() throws UserNotFoundException {
		List<UserModel> allUsers = userService.getAllUsers();

		for (UserModel user : allUsers) {
			int ogId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(ogId).withSelfRel();
			user.add(selfLink);

			CollectionModel<OrderModel> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(ogId);
			Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderLink);
		}
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		return new CollectionModel<UserModel>(allUsers, selfLink);

	}

	@GetMapping("/{id}")
	public EntityModel<UserModel> getUserById(@PathVariable @Min(1) int id) {
		try {
			Optional<UserModel> optionalUser = userService.getUserById(id);
			UserModel user = optionalUser.get();
			int ogId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(ogId).withSelfRel();
			user.add(selfLink);
			return new EntityModel<UserModel>(user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
