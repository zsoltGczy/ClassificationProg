package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;
import com.classproject.service.MixtureComponentService;

@Service
public class AcuteToxicityService {

	private MixtureComponentService componentService;
	
	@Autowired
	public void setComponentService(MixtureComponentService componentService) {
		this.componentService = componentService;
	}
	



	private final String nameCat1 = "Acute Tox. 1";
	private final String nameCat2 = "Acute Tox. 2";
	private final String nameCat3 = "Acute Tox. 3";
	private final String nameCat4 = "Acute Tox. 4"; 
	
	private final String pictogramCat1Cat2Cat3 = "GHS06";
	private final String pictogramCat4 = "GHS07";
	
	private final String signalWordCat1Cat2Cat3 = "Danger";
	private final String signalWordCat4 = "Warning";
	
	
	
	protected void acuteTox(Mixture mixture) {
		
		boolean acuteToxOral = false;
		boolean acuteToxDermal = false;
		boolean acuteToxInhalGases = false;
		boolean acuteToxInhalVapours = false;
		boolean acuteToxInhalDustMist = false;
		
		for(MixtureComponent comp : mixture.getMixtureComponents()) {
		acuteToxOral = componentHasAcutetox(comp, "Acute Tox. 1 (H300)", "Acute Tox. 2 (H300)",
					"Acute Tox. 3 (H301)", "Acute Tox. 4 (H302)");
			
		acuteToxDermal = componentHasAcutetox(comp, "Acute Tox. 1 (H310)", "Acute Tox. 2 (H310)",
					"Acute Tox. 3 (H311)", "Acute Tox. 4 (H312)");
			
		acuteToxInhalGases = componentHasAcutetox(comp, "Acute Tox. 1 (H330) - Gases", "Acute Tox. 2 (H330) - Gases",
					"Acute Tox. 3 (H331) - Gases", "Acute Tox. 4 (H332) - Gases");
			
		acuteToxInhalVapours = componentHasAcutetox(comp, "Acute Tox. 1 (H330) - Vapours", "Acute Tox. 2 (H330) - Vapours",
					"Acute Tox. 3 (H331) - Vapours", "Acute Tox. 4 (H332) - Vapours");
			
		acuteToxInhalDustMist = componentHasAcutetox(comp, "Acute Tox. 1 (H330) - Dust/mist", "Acute Tox. 2 (H330) - Dust/mist",
					"Acute Tox. 3 (H331) - Dust/mist", "Acute Tox. 4 (H332) - Dust/mist");
		}
		
		if(acuteToxOral) {
			acuteToxOral(mixture);
		}
		if(acuteToxDermal) {
			acuteToxDermal(mixture);
		}
		if(acuteToxInhalGases) {
			acuteToxInhal(mixture, 10.0, 100.0, 700.0, 4500.0, 100.0, 500.0, 2500.0, 20000.0, "Gases");
		}
		if(acuteToxInhalVapours) {
			acuteToxInhal(mixture, 0.05, 0.5, 3.0, 11.0, 0.5, 2.0, 10.0, 20.0, "Vapours");
		}
		if(acuteToxInhalDustMist) {
			acuteToxInhal(mixture, 0.005, 0.05, 0.5, 1.5, 0.05, 0.5, 1.0, 5.0, "Dust/mist");
		}
	}
	
	

	private boolean componentHasAcutetox(MixtureComponent comp, String acuteTox1, String acuteTox2, String acuteTox3,
			String acuteTox4) {

		if (GeneralFunctions.componentHasHazard(comp, acuteTox1) || GeneralFunctions.componentHasHazard(comp, acuteTox2)
				|| GeneralFunctions.componentHasHazard(comp, acuteTox3)
				|| GeneralFunctions.componentHasHazard(comp, acuteTox4)) {
			return true;
		}
		return false;
	}
	
	
	
	private void acuteToxInhal(Mixture mixture, double limitValue1, double limitValue2, double limitValue3,
			double limitValue4, double interval1, double interval2, double interval3, double interval4,
			String acutetoxInhalType) {

		double ateMix = correctedWithUnknownAcuteToxValue(mixture)
				/ concDivAteInhalation(mixture, acutetoxInhalType, limitValue1, limitValue2, limitValue3, limitValue4);
		
		String hazardStatementCat1Cat2 = "H330:Fatal if inhaled";
		String hazardStatementCat3 = "H331: Toxic if inhaled";
		String hazardStatementCat4 = "H332: Harmful if inhaled";

		String precautStatementCat1Cat2 = "P260, P271, P284, P304+P340, P310, P320, P403+P233, P405, P501";
		String precautStatementCat3 = "P261, P271, P304+P340, P311, P321, P403+P233, P405, P501";
		String precautStatementCat4 = "P261, P271, P304+P340, P312";

		if (ateMix > 0 && ateMix <= interval1) {
			GeneralFunctions.addNewHazard(mixture, nameCat1, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat1Cat2);
		} else if (ateMix > interval1 && ateMix <= interval2) {
			GeneralFunctions.addNewHazard(mixture, nameCat2, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat4);
		} else if (ateMix > interval2 && ateMix <= interval3) {
			GeneralFunctions.addNewHazard(mixture, nameCat3, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat3, precautStatementCat3);
		} else if (ateMix > interval3 && ateMix <= interval4) {
			GeneralFunctions.addNewHazard(mixture, nameCat4, pictogramCat4, signalWordCat4, hazardStatementCat4, precautStatementCat4);
		} 
	}
	
	
	private void acuteToxDermal(Mixture mixture) {

		double ateMix = correctedWithUnknownAcuteToxValue(mixture) / concDivAteDermal(mixture);

		String hazardStatementCat1Cat2 = "H310:Fatal in contact with skin";
		String hazardStatementCat3 = "H311: Toxic in contact with skin";
		String hazardStatementCat4 = "H312: Harmful in contact with skin";

		String precautStatementCat1Cat2 = "P262, P264, P270, P280, P302+P352, P310, P321, P361+P364, P405, P501";
		String precautStatementCat3 = "P280, P302+P352, P312, P321, P361+P364, P405, P501";
		String precautStatementCat4 = "P280, P302+P352, P312, P321, P362+P364, P405, P501";

		if (ateMix > 0 && ateMix <= 50) {
			GeneralFunctions.addNewHazard(mixture, nameCat1, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat1Cat2);
		} else if (ateMix > 50 && ateMix <= 200) {
			GeneralFunctions.addNewHazard(mixture, nameCat2, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat1Cat2);
		} else if (ateMix > 200 && ateMix <= 1000) {
			GeneralFunctions.addNewHazard(mixture, nameCat3, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat3, precautStatementCat3);
		} else if (ateMix > 1000 && ateMix <= 2000) {
			GeneralFunctions.addNewHazard(mixture, nameCat4, pictogramCat4, signalWordCat4, hazardStatementCat4, precautStatementCat4);
		}
	}
	
	private void acuteToxOral(Mixture mixture) {

		double ateMix = correctedWithUnknownAcuteToxValue(mixture) / concDivAteOral(mixture);
		
		String hazardStatementCat1Cat2 = "H300: Fatal if swallowed";
		String hazardStatementCat3 = "H301: Toxic if swallowed";
		String hazardStatementCat4 = "H302: Harmful if swallowed";

		String precautStatementCat1Cat2Cat3 = "P264, P270, P301+P310, P321, P330, P405, P501";
		String precautStatementCat4 = "P264, P270, P301+P312, P330, P501";

		if (ateMix > 0 && ateMix <= 5) {
			GeneralFunctions.addNewHazard(mixture, nameCat1, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat1Cat2Cat3);
		} else if (ateMix > 5 && ateMix <= 50) {
			GeneralFunctions.addNewHazard(mixture, nameCat2, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat1Cat2, precautStatementCat1Cat2Cat3);
		} else if (ateMix > 50 && ateMix <= 300) {
			GeneralFunctions.addNewHazard(mixture, nameCat3, pictogramCat1Cat2Cat3, signalWordCat1Cat2Cat3, hazardStatementCat3, precautStatementCat1Cat2Cat3);
		} else if (ateMix > 300 && ateMix <= 2000) {
			GeneralFunctions.addNewHazard(mixture, nameCat4, pictogramCat4, signalWordCat4, hazardStatementCat4, precautStatementCat4);
		}
	}
	
	
	
	
	private double concDivAteInhalation(Mixture mixture, String acutetoxInhalType, double limitValue1,
			double limitValue2, double limitValue3, double limitValue4) {

		String specificConcentrationLimitName = "inhalation: ATE";
		double concDivAte = 0.0;
		for (MixtureComponent comp : componentService.getMixComponents(mixture)) {
			concDivAte += concDivAte(comp, "Acute Tox. 1 (H330) - " + acutetoxInhalType, specificConcentrationLimitName,
					limitValue1);
			concDivAte += concDivAte(comp, "Acute Tox. 2 (H330) - " + acutetoxInhalType, specificConcentrationLimitName,
					limitValue2);
			concDivAte += concDivAte(comp, "Acute Tox. 3 (H331) - " + acutetoxInhalType, specificConcentrationLimitName,
					limitValue3);
			concDivAte += concDivAte(comp, "Acute Tox. 4 (H332) - " + acutetoxInhalType, specificConcentrationLimitName,
					limitValue4);
		}
		return concDivAte;
	}
	
	private double concDivAteDermal(Mixture mixture) {

		String specificConcentrationLimitName = "dermal: ATE";
		double concDivAte = 0.0;

		for (MixtureComponent comp : componentService.getMixComponents(mixture)) {
			concDivAte += concDivAte(comp, "Acute Tox. 1 (H310)", specificConcentrationLimitName, 5.0);
			concDivAte += concDivAte(comp, "Acute Tox. 2 (H310)", specificConcentrationLimitName, 50.0);
			concDivAte += concDivAte(comp, "Acute Tox. 3 (H311)", specificConcentrationLimitName, 300.0);
			concDivAte += concDivAte(comp, "Acute Tox. 4 (H312)", specificConcentrationLimitName, 1100.0);
		}

		return concDivAte;
	}
	
	private double concDivAteOral(Mixture mixture) {

		String specificConcentrationLimitName = "oral: ATE";
		double concDivAte = 0.0;
		for (MixtureComponent comp : componentService.getMixComponents(mixture)) {
			concDivAte += concDivAte(comp, "Acute Tox. 1 (H300)", specificConcentrationLimitName, 0.5);
			concDivAte += concDivAte(comp, "Acute Tox. 2 (H300)", specificConcentrationLimitName, 5.0);
			concDivAte += concDivAte(comp, "Acute Tox. 3 (H301)", specificConcentrationLimitName, 100.0);
			concDivAte += concDivAte(comp, "Acute Tox. 4 (H302)", specificConcentrationLimitName, 500.0);
		}
		return concDivAte;
	}
	
	
	
	
	
	private double concDivAte(MixtureComponent comp, String hazard, String specificName, double ate) {
		
		double sumOfRelevantConcentrations = 0.0;
		double cutOff = hazard.contains(nameCat4) ? 1.0 : 0.1;
		
			if(comp.getConcentration() >= cutOff) {
				if(comp.getHazards().contains(hazard)) {
					sumOfRelevantConcentrations += GeneralFunctions.additiveHazardClassCalculation(comp, specificName, ate, cutOff);
				}
			}
		return sumOfRelevantConcentrations;
	}

	
	private double correctedWithUnknownAcuteToxValue(Mixture mixture) {

		double result = 100.0;
		if (mixture.getUnknownAcuteToxValues() != null)
			result = mixture.getUnknownAcuteToxValues() > 10 ? 100.0 - mixture.getUnknownAcuteToxValues() : 100.0;

		return result;
	}
	
	
}
