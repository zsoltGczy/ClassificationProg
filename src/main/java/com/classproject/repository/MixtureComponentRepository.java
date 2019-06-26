package com.classproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.classproject.domain.MixtureComponent;
import com.classproject.domain.Mixture;

public interface MixtureComponentRepository extends CrudRepository<MixtureComponent, Long> {
	
	List<MixtureComponent> findMixtureComponentByMixture(Mixture mixture);
	
}
