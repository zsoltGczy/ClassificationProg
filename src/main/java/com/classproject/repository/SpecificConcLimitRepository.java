package com.classproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.classproject.domain.MixtureComponent;
import com.classproject.domain.SpecificConcLimit;

public interface SpecificConcLimitRepository extends CrudRepository<SpecificConcLimit, Long> {

	List<SpecificConcLimit> findSpecificConcLimitByMixtureComponent(MixtureComponent component);
}
