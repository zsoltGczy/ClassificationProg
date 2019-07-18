package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class LactationService {
	

	private final String lactation = "Lact. (H362)";
	private final String nameCatLactation = "Lact.";
	private final String hazardStatementCatLactation = "H362: May cause harm to breast-fed children";
	private final String precautStatementCatLactation = "P201, P260, P263, P264, P270, P308+P313";


	

	
	protected void lactationAll(Mixture mixture) {
		addLactationHazard(mixture, 0.3); 
	}
	
	private void addLactationHazard(Mixture mixture, double concLimit) {
		
		if (lactation(mixture, lactation, concLimit)) {
			addLactationHazard(mixture);
		}
	}
	
	
	
	private void addLactationHazard(Mixture mixture) {
		GeneralFunctions.addNewHazard(mixture, nameCatLactation, "", "", hazardStatementCatLactation, precautStatementCatLactation);
	}
	
	
	private boolean lactation(Mixture mixture, String hazard, double concLimit) {
		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}
	
		
}
