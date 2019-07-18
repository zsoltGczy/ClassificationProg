package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class AcuteAquaticToxicityService {
	
	


	private final String aquaticAcute1 = "Aquatic Acute 1 (H400)";
	private final String nameCatAquaticAcute1 = "Aquatic Acute 1";
	private final String pictogramCatAquaticAcute1 = "GHS09";
	private final String signalWordCatAquaticAcute1 = "Warning";
	private final String hazardStatementCatAquaticAcute1 = "H400: Very toxic to aquatic life";
	private final String precautStatementCatAquaticAcute1 = "P273, P391, P501";
	
	protected void aquaticAcute1 (Mixture mixture) {
		double aqAcute1 = 0.0;
		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			aqAcute1 += aquaticAcuteCalculation(comp);
		}
		if (aqAcute1 >= 25) {
			GeneralFunctions.addNewHazard(mixture, nameCatAquaticAcute1, pictogramCatAquaticAcute1,
					signalWordCatAquaticAcute1, hazardStatementCatAquaticAcute1, precautStatementCatAquaticAcute1);
		}
	}
	
	
	private double aquaticAcuteCalculation(MixtureComponent comp) {
		return GeneralFunctions.aquaticAcute1AndChronic1(comp, aquaticAcute1, "M-factor Aquatic Acute");
	}
	
		
}
