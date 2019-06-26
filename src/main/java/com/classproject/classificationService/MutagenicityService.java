package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class MutagenicityService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	
	
	


	private final String mutagenicity1A = "Muta. 1A (H340)";
	private final String mutagenicity1B = "Muta. 1B (H340)";
	private final String mutagenicity2 = "Muta. 2 (H341)";
	
	private final String nameCatMutagenicity1A = "Muta. 1A";
	private final String nameCatMutagenicity1B = "Muta. 1B";
	private final String nameCatMutagenicity2 = "Muta. 2";
	
	private final String pictogramCatMutagenicity1A1B2 = "GHS08";
	
	private final String signalWordCatMutagenicity1A1B = "Danger";
	private final String signalWordCatMutagenicity2 = "Warning";
	
	private final String hazardStatementCatMutagenicity1A1B = "H340: May cause genetic defects (state route of exposure if\r\n" + 
			"it is conclusively proven that no other routes of exposure cause the hazard)";
	private final String hazardStatementCatMutagenicity2 = "H341: Suspected of causing genetic defects (state route\r\n" + 
			"of exposure if it is conclusively proven that no other routes of exposure cause the hazard)";
	
	private final String precautStatementCatMutagenicity1A1B2 = "P201, P202, P280, P308+P313, P405, P501";


	

	
	protected void mutagenicityAll(Mixture mixture) {
		addAllMutagenicityHazards(mixture, 0.1, 1);
	}
	
	
	
	
	
	private void addAllMutagenicityHazards(Mixture mixture, double concLimit1A1B, double concLimit2) {
		
		if (mutagenicity(mixture, mutagenicity1A, concLimit1A1B)) {
			addMutagenicityHazards1A1B(mixture, nameCatMutagenicity1A);
		}
		else if(mutagenicity(mixture, mutagenicity1B, concLimit1A1B)) {
			addMutagenicityHazards1A1B(mixture, nameCatMutagenicity1B);
		}
		else if(mutagenicity(mixture, mutagenicity2, concLimit2)) {
			addMutagenicityHazards2(mixture);
		}
	}
	
	
	private void addMutagenicityHazards2(Mixture mixture) {
		generalFunctions.addNewHazard(mixture, nameCatMutagenicity2, pictogramCatMutagenicity1A1B2, signalWordCatMutagenicity2,
				hazardStatementCatMutagenicity2, precautStatementCatMutagenicity1A1B2);
	}
	
	
	private void addMutagenicityHazards1A1B(Mixture mixture, String name) {
		generalFunctions.addNewHazard(mixture, name, pictogramCatMutagenicity1A1B2, signalWordCatMutagenicity1A1B,
				hazardStatementCatMutagenicity1A1B, precautStatementCatMutagenicity1A1B2);
	}
	
	
	private boolean mutagenicity(Mixture mixture, String hazard, double concLimit) {
		return generalFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}
	
		
}
