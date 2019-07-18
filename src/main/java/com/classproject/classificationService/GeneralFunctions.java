package com.classproject.classificationService;

import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;
import com.classproject.domain.MixtureHazard;
import com.classproject.domain.SpecificConcLimit;

@Service
public class GeneralFunctions {
	

	protected static double aquaticAcute1AndChronic1(MixtureComponent comp, String hazard, String specificName) {
		if (componentHasHazard(comp, hazard)) {
			if (!comp.getSpecificConcLimits().isEmpty()) {
				for (SpecificConcLimit spec : comp.getSpecificConcLimits()) {
					if (spec.getName().equals(specificName)) {
						return (comp.getConcentration() * (spec.getValue() == null ? 1 : spec.getValue()));
					}
				}
			} else if (comp.getConcentration() >= 0.1) {
				return comp.getConcentration();
			}
		}
		return 0.0;
	}
	
	
	
	
	protected static double additiveHazardClassCalculation(MixtureComponent comp, String specificName,
			double divisor, double cutOff) {

		double sumOfRelevantConcentrations = 0.0;
		double specific = 0.0;

		if (!comp.getSpecificConcLimits().isEmpty()) {
			for (SpecificConcLimit spec : comp.getSpecificConcLimits()) {
				if (spec.getName().equals(specificName)) {
					specific += comp.getConcentration() / spec.getValue();
				}
			}
		}
		if (specific > 0.0) {
			sumOfRelevantConcentrations += specific;
		} else if (comp.getConcentration() >= cutOff) {
			sumOfRelevantConcentrations += comp.getConcentration() / divisor;
		}
		return sumOfRelevantConcentrations;
	}
	
	
	
	
	protected static boolean specialCase(Mixture mixture, String hazard, String specificName) {

		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			if (comp.getHazards().contains(hazard)) {
				return componentConcHigherThanSpecificConc(comp, specificName, false);
			}
		}
		return false;
	}
	
	
	
	
	
	protected static boolean nonAdditiveHazardClassCalculation(Mixture mixture, String hazard, double concLimit,
			String genericOrElicitation) {

		int trueCaseCounter = 0;

		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			int specialCaseCounter = 0;
			if (componentHasHazard(comp, hazard)) {
				if (!comp.getSpecificConcLimits().isEmpty()) {

					for (SpecificConcLimit spec : comp.getSpecificConcLimits()) {
						if (spec.getName().equals(hazard)) {

							double specValue = genericOrElicitation.equals("elicitacion") ? spec.getValue() / 10
									: spec.getValue();

							if (comp.getConcentration() >= specValue) {
								trueCaseCounter++;
							} else {
								specialCaseCounter++;
							}
						}
					}
				}
				if (specialCaseCounter == 0 && comp.getConcentration() >= concLimit) {
					trueCaseCounter++;
				}
			}
		}
		 return trueCaseCounter > 0 ? true : false;
	}

	
	
	
	protected static boolean componentConcHigherThanSpecificConc(Mixture mixture, String specificConcName) {

		for (MixtureComponent comp : mixture.getMixtureComponents()) {
			componentConcHigherThanSpecificConc(comp, specificConcName, true);
		}
		return true;
	}
	
	
	
	protected static boolean componentConcHigherThanSpecificConc(MixtureComponent component, String specificConcName, boolean elseValue) {

		if (!component.getSpecificConcLimits().isEmpty()) {
			for (SpecificConcLimit spec : component.getSpecificConcLimits()) {
				if (spec.getName().equals(specificConcName)) {
					return component.getConcentration() >= spec.getValue();
				}
			}
		}
		return elseValue;
	}

	
	protected static boolean componentHasHazard(MixtureComponent comp, String hazard) {
		if (comp.getHazards().contains(hazard))
			return true;
		return false;
	}

	
	
	protected static void addNewHazard(Mixture mixture, String name, String pictogram, String signalWord,
			String hazardStatement, String precautStatement) {

		mixture.getHazardsOfMixture()
				.add(new MixtureHazard(name, pictogram, signalWord, hazardStatement, precautStatement, mixture));
	}
	
}
