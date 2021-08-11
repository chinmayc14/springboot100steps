package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.ui.models.UserModel;

public interface UserService {
	List<UserModel> getAllUsers();

	UserModel createUser(UserModel user);

	Optional<UserModel> getUserById(int id);

	UserModel updateUser(UserModel user);

	void deleteUser(int id);

	UserModel getUserByUsername(String username);

}
