package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class StotREService {
	
	
	private final String stotRE1 = "STOT RE 1 (H372)";
	private final String stotRE2 = "STOT RE 2 (H373)";
	
	private final String nameCatStotRE1 = "STOT RE 1";
	private final String nameCatStotRE2 = "STOT RE 2";
	
	private final String pictogramCatStotRE1and2 = "GHS08";
	
	private final String signalWordCatStotRE1 = "Danger";
	private final String signalWordCatStotRE2 = "Warning";
	
	private final String hazardStatementCatStotRE1 = "H372: Causes damage to organs "
			+ "(state all organs affected, if known) through prolonged or repeated exposure "
			+ "(state route of exposure if it is conclusively proven that no other routes of "
			+ "exposure cause the hazard)";
	private final String hazardStatementCatStotRE2 = "H372: May cause damage to organs "
			+ "(state all organs affected, if known) through prolonged or repeated exposure "
			+ "(state route of exposure if it is conclusively proven that no other routes of "
			+ "exposure cause the hazard)";
	
	private final String precautStatementCatStotRE1 = "P260, P264, P270, P314, P501";
	private final String precautStatementCatStotRE2 = "P260, P314, P501";
	
	

	
	
	
	protected void addAllstotREHazards(Mixture mixture) {
		
		if(stotRE1and2(mixture, stotRE1, 10)) {
			addStotREHazard1(mixture);
		} else if (GeneralFunctions.specialCase(mixture, stotRE1, stotRE2) || stotRE1and2(mixture, stotRE1, 1)
				|| stotRE1and2(mixture, stotRE2, 10)) {
			addStotREHazard2(mixture);
		}
	}
	
	
	
	
	private void addStotREHazard2(Mixture mixture) {
		GeneralFunctions.addNewHazard(mixture, nameCatStotRE2, pictogramCatStotRE1and2, signalWordCatStotRE2,
				hazardStatementCatStotRE2, precautStatementCatStotRE2);
	}
	
	private void addStotREHazard1(Mixture mixture) {
		GeneralFunctions.addNewHazard(mixture, nameCatStotRE1, pictogramCatStotRE1and2, signalWordCatStotRE1,
				hazardStatementCatStotRE1, precautStatementCatStotRE1);
	}
	
	
	
	
	private boolean stotRE1and2(Mixture mixture, String hazard, double concLimit) {
		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}
	
		
}
