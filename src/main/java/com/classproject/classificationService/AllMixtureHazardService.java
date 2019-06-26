package com.classproject.classificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classproject.domain.Mixture;

@Service
public class AllMixtureHazardService {

	private AcuteToxicityService acuteToxicityService;
	private SkinCorrIrritService skinCorrIrritService;
	private EyeDamIrritService eyeDamIrritService;
	private RespSensService respSensService;
	private SkinSensService skinSensService;
	private MutagenicityService mutagenicityService;
	private CarcinogenicityService carcinogenicityService;
	private ReproductiveToxicityService reproductiveToxicityService;
	private LactationService lactationService;
	private StotSEService stotSEService;
	private StotREService stotREService;
	private AspirationService aspirationService;
	private AcuteAquaticToxicityService acuteAquaticToxicityService;
	private ChronicAquaticToxicityService chronicAquaticToxicityService;
	private OzoneService ozoneService;
	
	
	@Autowired
	public void setAcuteToxicityService(AcuteToxicityService acuteToxicityService) {
		this.acuteToxicityService = acuteToxicityService;
	}
	@Autowired
	public void setSkinCorrIrritService(SkinCorrIrritService skinCorrIrritService) {
		this.skinCorrIrritService = skinCorrIrritService;
	}
	@Autowired
	public void setEyeDamIrritService(EyeDamIrritService eyeDamIrritService) {
		this.eyeDamIrritService = eyeDamIrritService;
	}
	@Autowired
	public void setRespSensService(RespSensService respSensService) {
		this.respSensService = respSensService;
	}
	@Autowired
	public void setSkinSensService(SkinSensService skinSensService) {
		this.skinSensService = skinSensService;
	}
	@Autowired
	public void setMutagenicityService(MutagenicityService mutagenicityService) {
		this.mutagenicityService = mutagenicityService;
	}
	@Autowired
	public void setCarcinogenicityService(CarcinogenicityService carcinogenicityService) {
		this.carcinogenicityService = carcinogenicityService;
	}
	@Autowired
	public void setReproductiveToxicityService(ReproductiveToxicityService reproductiveToxicityService) {
		this.reproductiveToxicityService = reproductiveToxicityService;
	}
	@Autowired
	public void setLactationService(LactationService lactationService) {
		this.lactationService = lactationService;
	}
	@Autowired
	public void setStotSEService(StotSEService stotSEService) {
		this.stotSEService = stotSEService;
	}
	@Autowired
	public void setStotREService(StotREService stotREService) {
		this.stotREService = stotREService;
	}
	@Autowired
	public void setAspirationService(AspirationService aspirationService) {
		this.aspirationService = aspirationService;
	}
	@Autowired
	public void setAcuteAquaticToxicityService(AcuteAquaticToxicityService acuteAquaticToxicityService) {
		this.acuteAquaticToxicityService = acuteAquaticToxicityService;
	}
	@Autowired
	public void setChronicAquaticToxicityService(ChronicAquaticToxicityService chronicAquaticToxicityService) {
		this.chronicAquaticToxicityService = chronicAquaticToxicityService;
	}
	@Autowired
	public void setOzoneService(OzoneService ozoneService) {
		this.ozoneService = ozoneService;
	}
	
	
	
	
	
	
	
	
	public void allMixtureHazards(Mixture mixture) {
		
		acuteToxicityService.acuteTox(mixture);
		skinCorrIrritService.skinCorrSkinIrrit(mixture);
		eyeDamIrritService.eyeDamageIrrit(mixture);
		respSensService.respSensAll(mixture);
		skinSensService.skinSensAll(mixture);
		mutagenicityService.mutagenicityAll(mixture);
		carcinogenicityService.carcinogenicityAll(mixture);
		reproductiveToxicityService.reproductiveToxicityAll(mixture);
		lactationService.lactationAll(mixture);
		stotSEService.addAllstotSEHazards(mixture);
		stotREService.addAllstotREHazards(mixture);
		aspirationService.aspiration1(mixture);
		acuteAquaticToxicityService.aquaticAcute1(mixture);
		chronicAquaticToxicityService.chronicAcute1(mixture);
		ozoneService.ozone1(mixture);
	}
	
	
}
