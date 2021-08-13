package com.example.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exeptions.UserNotFoundException;
import com.example.repositories.UserRepository;
import com.example.ui.models.OrderModel;
import com.example.ui.models.UserModel;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{userId}/orders")
	public CollectionModel<OrderModel> getAllOrders(@PathVariable int userId) throws UserNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow((() -> new UserNotFoundException("User Not Found")));
		List<OrderModel> allOrders = user.getOrders();

		for (OrderModel order : allOrders) {
			int ogId = order.getOrderId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(ogId).withSelfRel();
			order.add(selfLink);

		}
		return new CollectionModel<OrderModel>(allOrders);
	}

//	@GetMapping("/{userId}/orders/{orderId}")
//	public OrderModel getOrderById(@PathVariable int userId, @PathVariable int orderId) {
//		try {
//			UserModel user = userRepository.findById(userId)
//					.orElseThrow((() -> new UserNotFoundException("User Not Found")));
//			OrderModel order = orderRepository.findById(orderId)
//					.orElseThrow((() -> new UserNotFoundException("Order Not Found")));
//			return order;
//		} catch (UserNotFoundException ex) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//		}
//
//	}

}
