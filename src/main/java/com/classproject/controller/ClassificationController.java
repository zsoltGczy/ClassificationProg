package com.classproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.classproject.classificationService.AllMixtureHazardService;
import com.classproject.domain.Mixture;
import com.classproject.service.HazardService;
import com.classproject.service.MixtureService;

@Controller
public class ClassificationController {
	
	private MixtureService mixService;
	private HazardService hazardService;
	private AllMixtureHazardService allMixtureHazardService;
	
	@Autowired
	public void setMixService(MixtureService mixService) {
		this.mixService = mixService;
	}
	@Autowired
	public void setHazardService(HazardService hazardService) {
		this.hazardService = hazardService;
	}
	@Autowired
	public void setAllMixtureHazardService(AllMixtureHazardService allMixtureHazardService) {
		this.allMixtureHazardService = allMixtureHazardService;
	}
	
	

	
	
	@RequestMapping("/{mixtureId}/classmixture")
	protected String mixtureClassification(@PathVariable(value="mixtureId") Long mixtureId, Model model) {
		
		Mixture specificMixture = mixService.getSpecificMixture(mixtureId);
		
		specificMixture.getHazardsOfMixture().clear();
		
		allMixtureHazardService.allMixtureHazards(specificMixture);
		
		hazardService.saveHazards(specificMixture.getHazardsOfMixture());
		
		model.addAttribute("mixture", specificMixture);
		
		return "classmixture";
	}
	
}
