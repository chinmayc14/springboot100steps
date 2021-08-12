package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.exeptions.UserExistsException;
import com.example.exeptions.UserNotFoundException;
import com.example.ui.models.UserModel;

public interface UserService {
	List<UserModel> getAllUsers();

	UserModel createUser(UserModel user) throws UserExistsException;

	Optional<UserModel> getUserById(int id) throws UserNotFoundException;

	UserModel updateUser(UserModel user, int id) throws Exception;

	void deleteUser(int id);

	UserModel getUserByUsername(String username);

}
