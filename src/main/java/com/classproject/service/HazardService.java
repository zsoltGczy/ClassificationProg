package com.classproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.MixtureHazard;
import com.classproject.repository.HazardRepository;

@Service
public class HazardService {

	HazardRepository hazardRepo;
	
	@Autowired
	public void setHazardRepo(HazardRepository hazardRepo) {
		this.hazardRepo = hazardRepo;
	}


	public MixtureHazard findHazard(Long id) {
		return hazardRepo.findById(id).get();
	}

	public void saveHazards(List<MixtureHazard> hazards) {
		hazardRepo.saveAll(hazards);
	}
}
