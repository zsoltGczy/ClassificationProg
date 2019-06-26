package com.classproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.repository.MixtureRepository;

@Service
public class MixtureService {

	private MixtureRepository mixrepo;
   
	@Autowired
	public void setMixrepo(MixtureRepository mixrepo) {
		this.mixrepo = mixrepo;
	}
	
	
	
	
	
	public List<Mixture> getMixtures(){
		return mixrepo.findAll();
	}
	public Mixture getSpecificMixture(Long mixtureId) {
		return mixrepo.findById(mixtureId).get();
	}
	
	
	public void mixtureSaveOrUpdate(Mixture mix) {
		mixrepo.save(mix);
	}
	
	
	public void deleteMixture(Mixture mixture) {
		mixrepo.delete(mixture);
	}
	
	
}
