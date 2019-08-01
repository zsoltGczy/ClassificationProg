package com.classproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.MixtureComponent;
import com.classproject.domain.SpecificConcLimit;
import com.classproject.repository.SpecificConcLimitRepository;

@Service
public class SpecificConcLimitsService {

	private SpecificConcLimitRepository specRepo;
	private MixtureComponentService compService;
	
	@Autowired
	public void setSpecRepo(SpecificConcLimitRepository specRepo) {
		this.specRepo = specRepo;
	}
	@Autowired
	public void setCompService(MixtureComponentService compService) {
		this.compService = compService;
	}
	
	



	public List<SpecificConcLimit> getComponentSpecificConcLimits(MixtureComponent component) {
		return specRepo.findSpecificConcLimitByMixtureComponent(compService.getSpecificComponent(component.getComponentId()));
	}
	
	public SpecificConcLimit getActualSpecificConcLimit(Long id) {
		return specRepo.findById(id).get();
	}
	
		
	public void specificConcLimitSaveOrUpdate(SpecificConcLimit spec) {
		specRepo.save(spec);
	}
	
	
	public void deleteSpecificConcLimit(SpecificConcLimit spec) {
		MixtureComponent comp = spec.getMixtureComponent();
		comp.getSpecificConcLimits().remove(spec);
		specRepo.delete(spec);
	}
}
