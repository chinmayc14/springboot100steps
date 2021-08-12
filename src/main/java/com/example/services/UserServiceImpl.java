package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.exeptions.UserExistsException;
import com.example.exeptions.UserNotFoundException;
import com.example.repositories.UserRepository;
import com.example.ui.models.UserModel;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	@Autowired
	UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserModel> allUsers = userRepository.findAll();
		return allUsers;

	}

	@Override
	public UserModel createUser(UserModel user) throws UserExistsException {
		if (userRepository.findUserByUsername(user.getUsername()) != null) {
			throw new UserExistsException("User with same username already exists");
		}
		UserModel createdUser = userRepository.save(user);
		return createdUser;
	}

	@Override
	public Optional<UserModel> getUserById(int id) throws UserNotFoundException {

		Optional<UserModel> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User not found in database");
		else
			return user;
	}

	@Override
	public UserModel updateUser(UserModel user, int id) throws Exception {
		if (userRepository.findById(id).isPresent()) {
			user.setId(id);
			return userRepository.save(user);
		} else {
			throw new Exception("User does not exists in database");
		}
	}

	@Override
	public void deleteUser(int id) throws ResponseStatusException {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
		}
	}

	@Override
	public UserModel getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

}
