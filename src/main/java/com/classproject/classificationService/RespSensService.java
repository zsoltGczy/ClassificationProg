package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class RespSensService {
	
	private final String respSens1 = "Resp. Sens. 1 (H334)";
	private final String respSens1A = "Resp. Sens. 1A (H334)";
	private final String respSens1B = "Resp. Sens. 1B (H334)";
	
	private final String nameCatRespSens1 = "Resp. Sens. 1";
	private final String nameCatRespSens1A = "Resp. Sens. 1A";
	private final String nameCatRespSens1B = "Resp. Sens. 1B";
	
	private final String pictogramCatRespSens11A1B = "GHS08";
	
	private final String signalWordCatRespSens11A1B = "Danger";
	
	private final String hazardStatementCatRespSens11A1B = "H334: May cause allergy or asthma symptoms or breathing difficulties if inhaled";
	
	private final String precautStatementCatRespSens11A1B = "P261, P284, P304+P340, P342+P311, P501";


	

	
	protected void respSensAll(Mixture mixture) {

		if (mixture.getPhysicalState().equals("liquid, solid")) {
			addAllRespSensHazards(mixture, 0.1, 1);
		} else {
			addAllRespSensHazards(mixture, 0.1, 0.2);
		}
	}
	
	
	
	private void addAllRespSensHazards(Mixture mixture, double concLimit1A, double concLimit) {
		
		if (respSensitisation(mixture, respSens1A, concLimit1A)) {
			addRespSensHazards(mixture, nameCatRespSens1A);
		}
		else if(respSensitisation(mixture, respSens1B, concLimit)) {
			addRespSensHazards(mixture, nameCatRespSens1B);
		}
		else if(respSensitisation(mixture, respSens1, concLimit)) {
			addRespSensHazards(mixture, nameCatRespSens1);
		}
	}
	
	private void addRespSensHazards(Mixture mixture, String name) {
		GeneralFunctions.addNewHazard(mixture, name, pictogramCatRespSens11A1B, signalWordCatRespSens11A1B,
				hazardStatementCatRespSens11A1B, precautStatementCatRespSens11A1B);
	}
	
	
	private boolean respSensitisation(Mixture mixture, String hazard, double concLimit) {
		return respSensitisation(mixture, hazard, concLimit, "generic");
	}
	
	
	protected boolean respSensitisation(Mixture mixture, String hazard, double concLimit, String genericOrElicitation) {
		return GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, hazard, concLimit, genericOrElicitation);
	}		
}
