package com.classproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.classproject.domain.MixtureHazard;

public interface HazardRepository extends CrudRepository<MixtureHazard, Long> {

}
