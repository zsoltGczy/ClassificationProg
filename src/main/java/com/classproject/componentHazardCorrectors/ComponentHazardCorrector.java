package com.classproject.componentHazardCorrectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.classproject.domain.MixtureComponent;

@Component
public class ComponentHazardCorrector {

	private AcuteToxicityAutoCorrection acuteToxCorrect;
	private SkinCorrIrritAutoCorrection skinCorrIrritCorrect;
	private EyeDamageIrritAutoCorrection eyeDamageIrritAutoCorrection;
	private RespSensAutoCorrection respSensAutoCorrection;
	private SkinSensAutoCorrection skinSensAutoCorrection;
	private MutagenicityAutoCorrection mutagenicityAutoCorrection;
	private CarcinogenicityAutoCorrection carcinogenicityAutoCorrection;
	private ReproductiveToxicityAutoCorrection reproductiveToxicityAutoCorrection;
	private StotSEAutoCorrection stotSEAutoCorrection;
	private StotREAutoCorrection stotREAutoCorrection;
	private ChronicAquaticAutoCorrection chronicAquaticAutoCorrection;
	
	@Autowired
	public void setAcuteToxCorrect(AcuteToxicityAutoCorrection acuteToxCorrect) {
		this.acuteToxCorrect = acuteToxCorrect;
	}
	@Autowired
	public void setSkinCorrIrritCorrect(SkinCorrIrritAutoCorrection skinCorrIrritCorrect) {
		this.skinCorrIrritCorrect = skinCorrIrritCorrect;
	}
	@Autowired
	public void setEyeDamageIrritAutoCorrection(EyeDamageIrritAutoCorrection eyeDamageIrritAutoCorrection) {
		this.eyeDamageIrritAutoCorrection = eyeDamageIrritAutoCorrection;
	}
	@Autowired
	public void setRespSensAutoCorrection(RespSensAutoCorrection respSensAutoCorrection) {
		this.respSensAutoCorrection = respSensAutoCorrection;
	}
	@Autowired
	public void setSkinSensAutoCorrection(SkinSensAutoCorrection skinSensAutoCorrection) {
		this.skinSensAutoCorrection = skinSensAutoCorrection;
	}
	@Autowired
	public void setMutagenicityAutoCorrection(MutagenicityAutoCorrection mutagenicityAutoCorrection) {
		this.mutagenicityAutoCorrection = mutagenicityAutoCorrection;
	}
	@Autowired
	public void setCarcinogenicityAutoCorrection(CarcinogenicityAutoCorrection carcinogenicityAutoCorrection) {
		this.carcinogenicityAutoCorrection = carcinogenicityAutoCorrection;
	}
	@Autowired
	public void setReproductiveToxicityAutoCorrection(ReproductiveToxicityAutoCorrection reproductiveToxicityAutoCorrection) {
		this.reproductiveToxicityAutoCorrection = reproductiveToxicityAutoCorrection;
	}
	@Autowired
	public void setStotSEAutoCorrection(StotSEAutoCorrection stotSEAutoCorrection) {
		this.stotSEAutoCorrection = stotSEAutoCorrection;
	}
	@Autowired
	public void setStotREAutoCorrection(StotREAutoCorrection stotREAutoCorrection) {
		this.stotREAutoCorrection = stotREAutoCorrection;
	}
	@Autowired
	public void setChronicAquaticAutoCorrection(ChronicAquaticAutoCorrection chronicAquaticAutoCorrection) {
		this.chronicAquaticAutoCorrection = chronicAquaticAutoCorrection;
	}
	
	
	
	
	
	
	
	public void componentHazardCorrectors(MixtureComponent component) {
		acuteToxCorrect.correctAcuteToxComponentHazards(component);
		skinCorrIrritCorrect.correctSkinCorrIrritComponentHazards(component);
		eyeDamageIrritAutoCorrection.correctEyeDamageIrritComponentHazards(component);
		respSensAutoCorrection.correctRespSens(component);
		skinSensAutoCorrection.correctSkinSens(component);
		mutagenicityAutoCorrection.correctMutagenicity(component);
		carcinogenicityAutoCorrection.correctCarcinogenicity(component);
		reproductiveToxicityAutoCorrection.correctReproductiveToxicity(component);
		stotSEAutoCorrection.correctStotSE(component);
		stotREAutoCorrection.correctStotRE(component);
		chronicAquaticAutoCorrection.correctSkinCorrIrritComponentHazards(component);
	}
}
