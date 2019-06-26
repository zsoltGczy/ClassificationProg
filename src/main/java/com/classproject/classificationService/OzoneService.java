package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class OzoneService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	
	
	


	private final String ozone1 = "Ozone 1 (H420)";
	private final String nameCatOzone1 = "Ozone 1";
	private final String pictogramCatOzone1 = "GHS07";
	private final String signalWordCatOzone1 = "Warning";
	private final String hazardStatementCatOzone1 = "H420: Harms public health and the environment by destroying "
			+ "ozone in the upper atmosphere";
	private final String precautStatementCatOzone1 = "P502";
	
	
	
	
	protected void ozone1 (Mixture mixture) {
		if (ozone1Calculation(mixture)) {
			generalFunctions.addNewHazard(mixture, nameCatOzone1, pictogramCatOzone1, signalWordCatOzone1, 
					hazardStatementCatOzone1, precautStatementCatOzone1);
		}
	}
	
	
	private boolean ozone1Calculation (Mixture mixture) {
		return generalFunctions.nonAdditiveHazardClassCalculation(mixture, ozone1, 0.1, "");
	}
	
		
}
