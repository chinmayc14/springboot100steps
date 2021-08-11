package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public UserModel createUser(UserModel user) {
		UserModel createdUser = userRepository.save(user);
		return createdUser;
	}

	@Override
	public Optional<UserModel> getUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		UserModel updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserModel getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

}
