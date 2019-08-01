package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class StotSEService {
	
	private final String stotSE1 = "STOT SE 1 (H370)";
	private final String stotSE2 = "STOT SE 2 (H371)";
	private final String stotSE335 = "STOT SE 3 (H335)";
	private final String stotSE336 = "STOT SE 3 (H336)";
	
	private final String nameCatStotSE1 = "STOT SE 1";
	private final String nameCatStotSE2 = "STOT SE 2";
	private final String nameCatStotSE335and336 = "STOT SE 3";
	
	private final String pictogramCatStotSE1and2 = "GHS08";
	private final String pictogramCatStotSE335and336 = "GHS07";
	
	private final String signalWordCatStotSE1 = "Danger";
	private final String signalWordCatStotSE2and335and336 = "Warning";
	
	private final String hazardStatementCatStotSE1 = "H370: Causes damage to organs "
			+ "(or state all organs affected, if known) (state route of exposure if it is "
			+ "conclusively proven that no other routes of exposure cause the hazard)";
	private final String hazardStatementCatStotSE2 = "H371: May cause damage to organs "
			+ "(or state all organs affected, if known) (state route of exposure if it is "
			+ "conclusively proven that no other routes of exposure cause the hazard)";
	private final String hazardStatementCatStotSE335 = "H335: May cause respiratory irritation";
	private final String hazardStatementCatStotSE336 = "H336: May cause drowsiness or dizziness";
	
	private final String precautStatementCatStotSE1 = "P260, P264, P270, P308+P311, P321, P405, P501";
	private final String precautStatementCatStotSE2 = "P260, P264, P270, P308+P311, P405, P501";
	private final String precautStatementCatStotSE335and336 = "P261, P271, P304+P340, P312, P403+P233, P405, P501";


	

	
	
	
	protected void addAllstotSEHazards(Mixture mixture) {

		if (stotSE1and2(mixture, stotSE1, 10)) {
			addStotSEHazard1(mixture);
		} else if (GeneralFunctions.specialCase(mixture, stotSE1, stotSE2) || stotSE1and2(mixture, stotSE1, 1)
				|| stotSE1and2(mixture, stotSE2, 10)) {
			addStotSEHazard2(mixture);
		} else {
			if (stotSE3(mixture, stotSE335)) {
				addStotSEHazards3(mixture, hazardStatementCatStotSE335);
			} 
			if (stotSE3(mixture, stotSE336)) {
				addStotSEHazards3(mixture, hazardStatementCatStotSE336);
			}
		}
	}
	
	
	
	
	private void addStotSEHazards3(Mixture mixture, String hazardStatement) {
		GeneralFunctions.addNewHazard(mixture, nameCatStotSE335and336, pictogramCatStotSE335and336, signalWordCatStotSE2and335and336,
				hazardStatement, precautStatementCatStotSE335and336);
	}
	
	private void addStotSEHazard2(Mixture mixture) {
		GeneralFunctions.addNewHazard(mixture, nameCatStotSE2, pictogramCatStotSE1and2, signalWordCatStotSE2and335and336,
				hazardStatementCatStotSE2, precautStatementCatStotSE2);
	}
	
	
	private void addStotSEHazard1(Mixture mixture) {
		GeneralFunctions.addNewHazard(mixture, nameCatStotSE1, pictogramCatStotSE1and2, signalWordCatStotSE1,
				hazardStatementCatStotSE1, precautStatementCatStotSE1);
	}
	
	
	
	private boolean stotSE3(Mixture mixture, String hazard) {
		
		double sum = 0.0;
		for(MixtureComponent comp : mixture.getMixtureComponents()) {
			
			if(GeneralFunctions.componentHasHazard(comp, hazard))
			sum += GeneralFunctions.additiveHazardClassCalculation(comp, hazard, 20, 0.0);
			
			if(GeneralFunctions.componentConcHigherThanSpecificConc(comp, hazard, false)) return true;
		}
		if(sum >= 1.0) return true;
		return false;
	}
	
	
	private boolean stotSE1and2(Mixture mixture, String hazard, double concLimit) {
		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, "");
	}		
}
