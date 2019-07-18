package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class EyeDamIrritService {
	
	private SkinCorrIrritService skinCorrIrritService;
		
	@Autowired
	public void setSkinCorrIrritService(SkinCorrIrritService skinCorrIrritService) {
		this.skinCorrIrritService = skinCorrIrritService;
	}

	



	private double sumOfRelevantConcentrations;
	
	private final String eyeDamage1 = "Eye Dam. 1 (H318)";
	private final String eyeIrrit2 = "Eye Irrit. 2 (H319)";
	
	private final String nameCatEyeDam = "Eye Dam. 1";
	private final String nameCatEyeIrrit = "Eye Irrit. 2";
	
	private final String pictogramCatEyeDam = "GHS05";
	private final String pictogramCatEyeIrrit = "GHS07";
	
	private final String signalWordCatEyeDam = "Danger";
	private final String signalWordCatEyeIrrit = "Warning"; 
	
	private final String hazardStatementCatEyeDam = "H318: Causes serious eye damage";
	private final String hazardStatementCatEyeIrrit = "H319: Causes serious eye irritation";
	
	private final String precautStatementCatEyeDam = "P280, P305+P351+P338, P310";
	private final String precautStatementCatEyeIrrit = "P264, P280, P305+P351+P338, P337+P313";
	
	
	
	
	protected void eyeDamageIrrit(Mixture mixture) {

		double catEyeDamage1 = calculateEyeDam(mixture);
		double cateyeIrrit2 = calculateEyeIrrit(mixture);
		Double pH = mixture.getpH();

		if ((catEyeDamage1 >= 1 && GeneralFunctions.componentConcHigherThanSpecificConc(mixture, eyeDamage1))) {

			GeneralFunctions.addNewHazard(mixture, nameCatEyeDam, pictogramCatEyeDam, signalWordCatEyeDam,
					hazardStatementCatEyeDam, precautStatementCatEyeDam);

		} else if (pH != (null) && (pH <= 2 || pH >= 11.5)) {
				GeneralFunctions.addNewHazard(mixture, nameCatEyeDam, pictogramCatEyeDam, signalWordCatEyeDam,
						hazardStatementCatEyeDam, precautStatementCatEyeDam);
		} else if (cateyeIrrit2 >= 1 && GeneralFunctions.componentConcHigherThanSpecificConc(mixture, eyeIrrit2)) {

			GeneralFunctions.addNewHazard(mixture, nameCatEyeIrrit, pictogramCatEyeIrrit, signalWordCatEyeIrrit,
					hazardStatementCatEyeIrrit, precautStatementCatEyeIrrit);
		}
	}
	
	
	
	private double calculateEyeIrrit(Mixture mixture) {
		
		sumOfRelevantConcentrations = 0.0;
		
		for (MixtureComponent comp : mixture.getMixtureComponents()) {

			sumOfRelevantConcentrations += calculationEyeIrrit(comp, skinCorrIrritService.skinCorr1A, 1);
			sumOfRelevantConcentrations += calculationEyeIrrit(comp, skinCorrIrritService.skinCorr1B, 1);
			sumOfRelevantConcentrations += calculationEyeIrrit(comp, skinCorrIrritService.skinCorr1C, 1);
			sumOfRelevantConcentrations += calculationEyeIrrit(comp, skinCorrIrritService.skinCorr1, 1);
			sumOfRelevantConcentrations += calculationEyeIrrit(comp, eyeDamage1, 1);
			sumOfRelevantConcentrations += calculationEyeIrrit(comp, eyeIrrit2, 10);
		}
		
		return sumOfRelevantConcentrations;
	}
	
	private double calculationEyeIrrit(MixtureComponent comp, String hazard, double divisor) {
		sumOfRelevantConcentrations = 0.0;
		if(GeneralFunctions.componentHasHazard(comp, hazard))
		sumOfRelevantConcentrations += GeneralFunctions.additiveHazardClassCalculation(comp, eyeIrrit2, divisor, skinCorrIrritService.cutOff);
		return sumOfRelevantConcentrations;
	}

	
	
	
	private double calculateEyeDam(Mixture mixture) {
		
		sumOfRelevantConcentrations = 0.0;
		
		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			
			sumOfRelevantConcentrations += calculationEyeDamWithSkinCorrHazards(comp, skinCorrIrritService.skinCorr1A);
			sumOfRelevantConcentrations += calculationEyeDamWithSkinCorrHazards(comp, skinCorrIrritService.skinCorr1B);
			sumOfRelevantConcentrations += calculationEyeDamWithSkinCorrHazards(comp, skinCorrIrritService.skinCorr1C);
			sumOfRelevantConcentrations += calculationEyeDamWithSkinCorrHazards(comp, skinCorrIrritService.skinCorr1);
			if (GeneralFunctions.componentHasHazard(comp, eyeDamage1)) {
				sumOfRelevantConcentrations += eyeDam(comp);
			}
		}
		
		return sumOfRelevantConcentrations;
	}
	
	
	private double calculationEyeDamWithSkinCorrHazards(MixtureComponent comp, String skinCorrHazard) {
		sumOfRelevantConcentrations = 0.0;
		if (GeneralFunctions.componentHasHazard(comp, skinCorrHazard) 
				&& !GeneralFunctions.componentHasHazard(comp, eyeDamage1)) {
			sumOfRelevantConcentrations += eyeDam(comp);
		}
		return sumOfRelevantConcentrations;
	}
	
	
	
	
	private double eyeDam(MixtureComponent comp) {
		return GeneralFunctions.additiveHazardClassCalculation(comp, eyeDamage1, 3, skinCorrIrritService.cutOff);
	}
	
}
