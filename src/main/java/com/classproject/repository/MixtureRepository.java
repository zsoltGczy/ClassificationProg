package com.classproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.classproject.domain.Mixture;

public interface MixtureRepository extends CrudRepository<Mixture, Long> {
	
	
	List<Mixture> findAll();
	
}
