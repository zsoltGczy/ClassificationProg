package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;

@Service
public class AspirationService {
	
	private GeneralFunctions generalFunctions;
		
	@Autowired
	public void setGeneralFunctions(GeneralFunctions generalFunctions) {
		this.generalFunctions = generalFunctions;
	}
	
	
	


	private final String aspiration1 = "Asp. Tox. 1 (H304)";
	private final String nameCatAspiration1 = "Asp. Tox. 1";
	private final String pictogramCatAspiration1 = "GHS08";
	private final String signalWordCatAspiration1 = "Danger";
	private final String hazardStatementCatAspiration1 = "H304: May be fatal if swallowed and enters airways";
	private final String precautStatementCatAspiration1 = "P260, P264, P270, P314, P501";
	
	
	
	
	protected void aspiration1(Mixture mixture) {
		double sumOfConcentrations = 0.0; 
		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			if (generalFunctions.componentHasHazard(comp, aspiration1)) {
				if (kinematicViscosity(mixture) != null) {
					sumOfConcentrations += aspiration1(comp, aspiration1);
					if (sumOfConcentrations >= 1 && kinematicViscosity(mixture) <= 20.5) {
						generalFunctions.addNewHazard(mixture, nameCatAspiration1, pictogramCatAspiration1,
								signalWordCatAspiration1, hazardStatementCatAspiration1,
								precautStatementCatAspiration1);
					}
				}
			}
		}
	}
	
	
	
	
	private Double kinematicViscosity(Mixture mixture) {
		if(mixture.getKinematicViscosity() != null) {
			return mixture.getKinematicViscosity();
		}else if(mixture.getDynamicViscosity() != null && mixture.getDensity() != null) {
			return mixture.getDynamicViscosity() / mixture.getDensity();
		}
		return null;
	}
	
	
	private double aspiration1(MixtureComponent comp, String specificName) {
		return generalFunctions.additiveHazardClassCalculation(comp, specificName, 10, 0.0);
	}
	
		
}
