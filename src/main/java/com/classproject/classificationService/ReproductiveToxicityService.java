package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class ReproductiveToxicityService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	
	
	


	private final String reproductiveToxicity1A = "Repr. 1A (H360)";
	private final String reproductiveToxicity1B = "Repr. 1B (H360)";
	private final String reproductiveToxicity2 = "Repr. 2 (H361)";
	
	private final String nameCatReproductiveToxicity1A = "Repr. 1A";
	private final String nameCatReproductiveToxicity1B = "Repr. 1B";
	private final String nameCatReproductiveToxicity2 = "Repr. 2";
	
	private final String pictogramCatReproductiveToxicity1A1B2 = "GHS08";
	
	private final String signalWordCatReproductiveToxicity1A1B = "Danger";
	private final String signalWordCatReproductiveToxicity2 = "Warning";
	
	private final String hazardStatementCatReproductiveToxicity1A1B = "H360: May damage fertility or the unborn child "
			+ "(state specific effect if known) (state route of exposure if it is conclusively proven "
			+ "that no other routes of exposure cause the hazard)";
	private final String hazardStatementCatReproductiveToxicity2 = "H361: Suspected of damaging fertility or the unborn child "
			+ "(state specific effect if known) (state route of exposure if it is conclusively proven "
			+ "that no other routes of exposure cause the hazard)";
	
	private final String precautStatementCatReproductiveToxicity1A1B2 = "P201, P202, P280, P308+P313, P405, P501";


	

	
	protected void reproductiveToxicityAll(Mixture mixture) {
		addAllReproductiveToxicityHazards(mixture, 0.3, 3); 
	}
	
	
	
	
	private void addAllReproductiveToxicityHazards(Mixture mixture, double concLimit1A1B, double concLimit2) {
		
		if (reproductiveToxicity(mixture, reproductiveToxicity1A, concLimit1A1B)) {
			addReproductiveToxicityHazards1A1B(mixture, nameCatReproductiveToxicity1A);
		}
		else if(reproductiveToxicity(mixture, reproductiveToxicity1B, concLimit1A1B)) {
			addReproductiveToxicityHazards1A1B(mixture, nameCatReproductiveToxicity1B);
		}
		else if(reproductiveToxicity(mixture, reproductiveToxicity2, concLimit2)) {
			addReproductiveToxicityHazards2(mixture);
		}
	}
	
	
	
	private void addReproductiveToxicityHazards2(Mixture mixture) {
		generalFunctions.addNewHazard(mixture, nameCatReproductiveToxicity2, pictogramCatReproductiveToxicity1A1B2, signalWordCatReproductiveToxicity2,
				hazardStatementCatReproductiveToxicity2, precautStatementCatReproductiveToxicity1A1B2);
	}
	
	
	private void addReproductiveToxicityHazards1A1B(Mixture mixture, String name) {
		generalFunctions.addNewHazard(mixture, name, pictogramCatReproductiveToxicity1A1B2, signalWordCatReproductiveToxicity1A1B,
				hazardStatementCatReproductiveToxicity1A1B, precautStatementCatReproductiveToxicity1A1B2);
	}
	
	
	private boolean reproductiveToxicity(Mixture mixture, String hazard, double concLimit) {
		return generalFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}
	
		
}
