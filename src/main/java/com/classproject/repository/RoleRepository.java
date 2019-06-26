package com.classproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.classproject.usersandroles.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);

	
}
