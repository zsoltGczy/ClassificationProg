package com.classproject.componentHazardCorrectors;

import org.springframework.stereotype.Component;

import com.classproject.domain.MixtureComponent;

@Component
public class ComponentHazardCorrector {

	public void componentHazardCorrectors(MixtureComponent component) {
		AcuteToxicityAutoCorrection.correctAcuteToxComponentHazards(component);
		SkinCorrIrritAutoCorrection.correctSkinCorrIrritComponentHazards(component);
		EyeDamageIrritAutoCorrection.correctEyeDamageIrritComponentHazards(component);
		RespSensAutoCorrection.correctRespSens(component);
		SkinSensAutoCorrection.correctSkinSens(component);
		MutagenicityAutoCorrection.correctMutagenicity(component);
		CarcinogenicityAutoCorrection.correctCarcinogenicity(component);
		ReproductiveToxicityAutoCorrection.correctReproductiveToxicity(component);
		StotSEAutoCorrection.correctStotSE(component);
		StotREAutoCorrection.correctStotRE(component);
		ChronicAquaticAutoCorrection.correctChronicAquaticComponentHazards(component);
	}
}
