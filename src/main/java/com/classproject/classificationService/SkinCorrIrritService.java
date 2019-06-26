package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class SkinCorrIrritService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	

	protected final double cutOff = 1.0;
	private double sumOfRelevantConcentrations;
	
	protected final String skinCorr1A = "Skin Corr. 1A (H314)";
	protected final String skinCorr1B = "Skin Corr. 1B (H314)";
	protected final String skinCorr1C = "Skin Corr. 1C (H314)";
	protected final String skinCorr1 = "Skin Corr. 1 (H314)";
	private final String skinIrrit = "Skin Irrit. 2 (H315)";
	
	private final String nameCat1A = "Skin Corr. 1A";
	private final String nameCat1B = "Skin Corr. 1B";
	private final String nameCat1C = "Skin Corr. 1C";
	private final String nameCat1 = "Skin Corr. 1";
	private final String nameCat2 = "Skin Irrit. 2";
	
	private final String pictogramCat1ABC = "GHS05";
	private final String pictogramCat2 = "GHS07";
	
	private final String signalWordCat1ABC = "Danger";
	private final String signalWordCat2 = "Warning"; 
	
	private final String hazardStatementCat1BC = "H314: Causes severe skin burns and eye damage";
	private final String hazardStatementCat2 = "H315: Causes skin irritation";
	
	private final String precautStatementCat1ABC = "P260, P264, P280, P301+P330+P331, P303+P361+P353, "
			+ "P363, P304+P340, P310, P321, P305+P351+P338, P405, P501";
	private final String precautStatementCat2 = "P264, P280, P302+P352, P321, P332+P313, P362+P364";
	
	
	
	
	protected void skinCorrSkinIrrit(Mixture mixture) {

		double cat1A = 0.0;
		double cat1B = 0.0;
		double cat1C = 0.0;
		double cat1 = 0.0;
		double catIrrit = calculateSkinIrritAll(mixture);
		Double pH = mixture.getpH();
		
		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			
			cat1A += calculateSkinCorr(comp, skinCorr1A);
			cat1B += calculateSkinCorr(comp, skinCorr1B);
			cat1C += calculateSkinCorr(comp, skinCorr1C);
			cat1 += calculateSkinCorr(comp, skinCorr1);
		}
		
		if (cat1A >= 1) {
			generalFunctions.addNewHazard(mixture, nameCat1A, pictogramCat1ABC, signalWordCat1ABC,
					hazardStatementCat1BC, precautStatementCat1ABC);
		} else if (generalFunctions.specialCase(mixture, skinCorr1A, skinCorr1B) || cat1A + cat1B >= 1) {
			generalFunctions.addNewHazard(mixture, nameCat1B, pictogramCat1ABC, signalWordCat1ABC,
					hazardStatementCat1BC, precautStatementCat1ABC);
		} else if (cat1A + cat1B + cat1C >= 1) {
			generalFunctions.addNewHazard(mixture, nameCat1C, pictogramCat1ABC, signalWordCat1ABC,
					hazardStatementCat1BC, precautStatementCat1ABC);
		} else if (cat1A + cat1B + cat1C + cat1 >= 1) {
			generalFunctions.addNewHazard(mixture, nameCat1, pictogramCat1ABC, signalWordCat1ABC, hazardStatementCat1BC,
					precautStatementCat1ABC);
		} else if (pH != (null) && (pH <= 2 || pH >= 11.5)) {
				generalFunctions.addNewHazard(mixture, nameCat1, pictogramCat1ABC, signalWordCat1ABC,
						hazardStatementCat1BC, precautStatementCat1ABC);
		} else if (catIrrit >= 1) {
			generalFunctions.addNewHazard(mixture, nameCat2, pictogramCat2, signalWordCat2, hazardStatementCat2,
					precautStatementCat2);
		}
	}
	
	
	
	private double calculateSkinIrritAll(Mixture mixture) {

		sumOfRelevantConcentrations = 0.0;

		for (MixtureComponent comp : mixture.getMixtureComponents()) {

			sumOfRelevantConcentrations += calculateSkinIrrit(comp, skinCorr1A, skinIrrit, 1);
			sumOfRelevantConcentrations += calculateSkinIrrit(comp, skinCorr1B, skinIrrit, 1);
			sumOfRelevantConcentrations += calculateSkinIrrit(comp, skinCorr1C, skinIrrit, 1);
			sumOfRelevantConcentrations += calculateSkinIrrit(comp, skinCorr1, skinIrrit, 1);
			sumOfRelevantConcentrations += calculateSkinIrrit(comp, skinIrrit, skinIrrit, 10);
		}
		return sumOfRelevantConcentrations;
	}
	
	
	
	private double calculateSkinIrrit(MixtureComponent comp, String hazard, String specificName, double divisor) {
		
		sumOfRelevantConcentrations = 0.0;
		if (generalFunctions.componentHasHazard(comp, hazard)) {
			sumOfRelevantConcentrations += generalFunctions.additiveHazardClassCalculation(comp, specificName, divisor, cutOff);;
		}
		return sumOfRelevantConcentrations;
	}
	
	private double calculateSkinCorr(MixtureComponent comp, String hazardAndSpecificName) {
		
		sumOfRelevantConcentrations = 0.0;
		if (generalFunctions.componentHasHazard(comp, hazardAndSpecificName)) {
			sumOfRelevantConcentrations += skinCorr(comp, hazardAndSpecificName);
		}
		return sumOfRelevantConcentrations;
	}
	
	
	private double skinCorr(MixtureComponent comp, String specificName) {
		return generalFunctions.additiveHazardClassCalculation(comp, specificName, 5, cutOff);
	}
		
	
}
