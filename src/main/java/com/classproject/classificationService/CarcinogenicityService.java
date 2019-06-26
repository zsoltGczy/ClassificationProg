package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class CarcinogenicityService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	
	
	


	private final String carcinogenicity1A = "Carc. 1A (H350)";
	private final String carcinogenicity1B = "Carc. 1B (H350)";
	private final String carcinogenicity2 = "Carc. 2 (H351)";
	
	private final String nameCatCarcinogenicity1A = "Carc. 1A";
	private final String nameCatCarcinogenicity1B = "Carc. 1B";
	private final String nameCatCarcinogenicity2 = "Carc. 2";
	
	private final String pictogramCatCarcinogenicity1A1B2 = "GHS08";
	
	private final String signalWordCatCarcinogenicity1A1B = "Danger";
	private final String signalWordCatCarcinogenicity2 = "Warning";
	
	private final String hazardStatementCatCarcinogenicity1A1B = "H350: May cause cancer (state route of exposure if it is\r\n" + 
			"conclusively proven that no other routes of exposure cause the hazard)";
	private final String hazardStatementCatCarcinogenicity2 = "H351: Suspected of causing cancer (state route of exposure if\r\n" + 
			"it is conclusively proven that no other routes of exposure cause the hazard)";
	
	private final String precautStatementCatCarcinogenicity1A1B2 = "P201, P202, P280, P308+P313, P405, P501";


	

	
	protected void carcinogenicityAll(Mixture mixture) {
		addAllCarcinogenicityHazards(mixture, 0.1, 1); 
	}
	
	
	
	
	
	private void addAllCarcinogenicityHazards(Mixture mixture, double concLimit1A1B, double concLimit2) {
		
		if (carcinogenicity(mixture, carcinogenicity1A, concLimit1A1B)) {
			addCarcinogenicityHazards1A1B(mixture, nameCatCarcinogenicity1A);
		}
		else if(carcinogenicity(mixture, carcinogenicity1B, concLimit1A1B)) {
			addCarcinogenicityHazards1A1B(mixture, nameCatCarcinogenicity1B);
		}
		else if(carcinogenicity(mixture, carcinogenicity2, concLimit2)) {
			addCarcinogenicityHazards2(mixture);
		}
	}
	
	
	private void addCarcinogenicityHazards2(Mixture mixture) {
		generalFunctions.addNewHazard(mixture, nameCatCarcinogenicity2, pictogramCatCarcinogenicity1A1B2, signalWordCatCarcinogenicity2,
				hazardStatementCatCarcinogenicity2, precautStatementCatCarcinogenicity1A1B2);
	}
	
	
	private void addCarcinogenicityHazards1A1B(Mixture mixture, String name) {
		generalFunctions.addNewHazard(mixture, name, pictogramCatCarcinogenicity1A1B2, signalWordCatCarcinogenicity1A1B,
				hazardStatementCatCarcinogenicity1A1B, precautStatementCatCarcinogenicity1A1B2);
	}
	
	
	private boolean carcinogenicity(Mixture mixture, String hazard, double concLimit) {
		return generalFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}
	
		
}
