package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class SkinSensService {
	

	private final String skinSens1 = "Skin. Sens. 1 (H317)";
	private final String skinSens1A = "Skin. Sens. 1A (H317)";
	private final String skinSens1B = "Skin. Sens. 1B (H317)";
	
	private final String nameCatSkinSens1 = "Skin. Sens. 1";
	private final String nameCatSkinSens1A = "Skin. Sens. 1A";
	private final String nameCatSkinSens1B = "Skin. Sens. 1B";
	
	private final String pictogramCatSkinSens11A1B = "GHS07";
	
	private final String signalWordCatSkinSens11A1B = "Warning";
	
	private final String hazardStatementCatSkinSens11A1B = "H317: May cause an allergic skin reaction";
	
	private final String precautStatementCatSkinSens11A1B = "P261, P272, P280, P302+P352, P333+P313, P321, P362+P364, P501";


	

	
	protected void skinSensAll(Mixture mixture) {
		addAllSkinSensHazards(mixture, 0.1, 1);
	}
	
//	protected void skinSensElicitacionAll(Mixture mixture) {
////		CLP II. melleklet 2.8. kell meg!!
//		boolean euh208 = skinSensitisation(mixture, "hazard", 0.01, "elicitation");
//	}
	
	
	
	
	private void addAllSkinSensHazards(Mixture mixture, double concLimit1A, double concLimit) {
		
		if (skinSensitisation(mixture, skinSens1A, concLimit1A)) {
			addSkinSensHazards(mixture, nameCatSkinSens1A);
		}
		else if(skinSensitisation(mixture, skinSens1B, concLimit)) {
			addSkinSensHazards(mixture, nameCatSkinSens1B);
		}
		else if(skinSensitisation(mixture, skinSens1, concLimit)) {
			addSkinSensHazards(mixture, nameCatSkinSens1);
		}
	}
	
	
	private void addSkinSensHazards(Mixture mixture, String name) {
		GeneralFunctions.addNewHazard(mixture, name, pictogramCatSkinSens11A1B, signalWordCatSkinSens11A1B,
				hazardStatementCatSkinSens11A1B, precautStatementCatSkinSens11A1B);
	}
	
	
	private boolean skinSensitisation(Mixture mixture, String hazard, double concLimit) {
		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "generic");
	}
	
//	private boolean skinSensitisation(Mixture mixture, String hazard, double concLimit, String genericOrElicitation) {
//		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, genericOrElicitation);
//	}
		
}
