package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ui.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	UserModel findUserByUsername(String username);

}
