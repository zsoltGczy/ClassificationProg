package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class ChronicAquaticToxicityService {
	


	private final String aquaticChronic1 = "Aquatic Chronic 1 (H410)";
	private final String aquaticChronic2 = "Aquatic Chronic 2 (H411)";
	private final String aquaticChronic3 = "Aquatic Chronic 3 (H412)";
	private final String aquaticChronic4 = "Aquatic Chronic 4 (H413)";
	
	private final String nameCatAquaticChronic1 = "Aquatic Chronic 1";
	private final String nameCatAquaticChronic2 = "Aquatic Chronic 2";
	private final String nameCatAquaticChronic3 = "Aquatic Chronic 3";
	private final String nameCatAquaticChronic4 = "Aquatic Chronic 4";
	
	private final String pictogramCatAquaticChronic1and2 = "GHS09";
	
	private final String signalWordCatAquaticChronic1 = "Warning";
	
	private final String hazardStatementCatAquaticChronic1 = "H410: Very toxic to aquatic life with long lasting effects";
	private final String hazardStatementCatAquaticChronic2 = "H411: Toxic to aquatic life with long lasting effects";
	private final String hazardStatementCatAquaticChronic3 = "H412: Harmful to aquatic life with long lasting effects";
	private final String hazardStatementCatAquaticChronic4 = "H413: May cause long lasting harmful effects to aquatic life";
	
	private final String precautStatementCatAquaticChronic1and2 = "P273, P391, P501";
	private final String precautStatementCatAquaticChronic3and4 = "P273, P501";
	
	
	
	
	protected void chronicAcute1 (Mixture mixture) {
		double aqChronic1 = 0.0;
		double aqChronic2 = 0.0;
		double aqChronic3 = 0.0;
		double aqChronic4 = 0.0;
		
		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			aqChronic1 += aquaticChronic1Calculation(comp);
			aqChronic2 += aquaticChronic2and3and4Calculation(comp, aquaticChronic2);
			aqChronic3 += aquaticChronic2and3and4Calculation(comp, aquaticChronic3);
			aqChronic4 += aquaticChronic2and3and4Calculation(comp, aquaticChronic4);
		}
		if (aqChronic1 >= 25) {
			GeneralFunctions.addNewHazard(mixture, nameCatAquaticChronic1, pictogramCatAquaticChronic1and2, 
					signalWordCatAquaticChronic1, hazardStatementCatAquaticChronic1, precautStatementCatAquaticChronic1and2);
		}else if((aqChronic1 * 10) + aqChronic2 >= 25) {
			GeneralFunctions.addNewHazard(mixture, nameCatAquaticChronic2, pictogramCatAquaticChronic1and2, 
					"", hazardStatementCatAquaticChronic2, precautStatementCatAquaticChronic1and2);
		}else if(((aqChronic1 * 100) + (aqChronic2 * 10)) + aqChronic3 >= 25) {
			GeneralFunctions.addNewHazard(mixture, nameCatAquaticChronic3, "", 
					"", hazardStatementCatAquaticChronic3, precautStatementCatAquaticChronic3and4);
		}else if(aqChronic1 + aqChronic2 + aqChronic3 + aqChronic4 >= 25) {
			GeneralFunctions.addNewHazard(mixture, nameCatAquaticChronic4, "", 
					"", hazardStatementCatAquaticChronic4, precautStatementCatAquaticChronic3and4);
		}
	}
	
	
	private double aquaticChronic2and3and4Calculation(MixtureComponent comp, String hazard) {
		if (GeneralFunctions.componentHasHazard(comp, hazard)) {
			if (comp.getConcentration() >= 1) {
				return comp.getConcentration();
			}
		}
		return 0.0;
	}
	
	
	private double aquaticChronic1Calculation(MixtureComponent comp) {
		return GeneralFunctions.aquaticAcute1AndChronic1(comp, aquaticChronic1, "M-factor Aquatic Chronic");
	}
	
}
