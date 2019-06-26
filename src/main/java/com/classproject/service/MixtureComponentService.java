package com.classproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.componentHazardCorrectors.ComponentHazardCorrector;
import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;
import com.classproject.repository.MixtureComponentRepository;


@Service
public class MixtureComponentService {
	
	private MixtureComponentRepository comprepo;
	private MixtureService mixService;
	private ComponentHazardCorrector componentHazardCorrector;
	
	@Autowired
	public void setComprepo(MixtureComponentRepository comprepo) {
		this.comprepo = comprepo;
	}
	@Autowired
	public void setMixService(MixtureService mixService) {
		this.mixService = mixService;
	}
	@Autowired
	public void setComponentHazardCorrector(ComponentHazardCorrector componentHazardCorrector) {
		this.componentHazardCorrector = componentHazardCorrector;
	}
	
	
	
	
	
	public List<MixtureComponent> getMixComponents(Mixture specificMixture) {
		return comprepo.findMixtureComponentByMixture(mixService.getSpecificMixture(specificMixture.getMixtureId()));
	}
	public MixtureComponent getSpecificComponent(Long componentId) {
		return comprepo.findById(componentId).get();
	}
	
	public void componentSaveOrUpdate(MixtureComponent component) {
		componentHazardCorrector.componentHazardCorrectors(component);
		comprepo.save(component);
	}

	
	
	
	public void deleteComponent(MixtureComponent component) {
		Mixture mix = component.getMixture();
		mix.getMixtureComponents().remove(component);
		comprepo.delete(component);
	}

	
}
