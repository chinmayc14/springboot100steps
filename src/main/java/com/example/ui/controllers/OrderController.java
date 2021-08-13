package com.example.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.exeptions.UserNotFoundException;
import com.example.repositories.OrderRepository;
import com.example.repositories.UserRepository;
import com.example.ui.models.OrderModel;
import com.example.ui.models.UserModel;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/{userId}/orders")
	public List<OrderModel> getAllOrders(@PathVariable int userId) throws UserNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow((() -> new UserNotFoundException("User Not Found")));
		return user.getOrders();
	}

	@PostMapping("/{userId}/orders")
	public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order, @PathVariable int userId)
			throws UserNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow((() -> new UserNotFoundException("User Not Found")));

		order.setUser(user);
		OrderModel createdOrder = orderRepository.save(order);
//		HttpHeaders headers = new HttpHeaders();
//			headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);

	}

	@GetMapping("/{userId}/orders/{orderId}")
	public OrderModel getOrderById(@PathVariable int userId, @PathVariable int orderId) {
		try {
			UserModel user = userRepository.findById(userId)
					.orElseThrow((() -> new UserNotFoundException("User Not Found")));
			OrderModel order = orderRepository.findById(orderId)
					.orElseThrow((() -> new UserNotFoundException("Order Not Found")));
			return order;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
