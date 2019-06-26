package com.classproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.classproject.usersandroles.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	User findByActivation(String code);
}
