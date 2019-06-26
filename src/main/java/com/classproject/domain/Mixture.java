package com.classproject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity(name="Mixtures")
public class Mixture {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long mixtureId;
	@NotEmpty(message = "Name must not be empty")
	@Size(min = 2, max = 32, message = "Name field must be between 2 and 32 characters long")
	private String mixtureName;
	@Range(min = 1, max = 14, message = "pH must be between 1 and 14")
	private Double pH;
	private Double kinematicViscosity;
	private Double dynamicViscosity;
	private Double density;
	@NotEmpty(message = "Physical state field must not be empty")
	private String physicalState;
	private Double unknownAcuteToxValues;
	@OneToMany(mappedBy="mixture", cascade=CascadeType.ALL)
	private List<MixtureComponent> mixtureComponents;
	@OneToMany(mappedBy="mixture", cascade=CascadeType.ALL)
	private List<MixtureHazard> hazardsOfMixture;
	
	


	public Long getMixtureId() {
		return mixtureId;
	}

	public void setMixtureId(Long mixtureId) {
		this.mixtureId = mixtureId;
	}

	public String getMixtureName() {
		return mixtureName;
	}

	public void setMixtureName(String mixtureName) {
		this.mixtureName = mixtureName;
	}

	public Double getpH() {
		return pH;
	}

	public void setpH(Double pH) {
		this.pH = pH;
	}

	public Double getKinematicViscosity() {
		return kinematicViscosity;
	}

	public void setKinematicViscosity(Double kinematicViscosity) {
		this.kinematicViscosity = kinematicViscosity;
	}

	public Double getDynamicViscosity() {
		return dynamicViscosity;
	}

	public void setDynamicViscosity(Double dynamicViscosity) {
		this.dynamicViscosity = dynamicViscosity;
	}

	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public String getPhysicalState() {
		return physicalState;
	}

	public void setPhysicalState(String physicalState) {
		this.physicalState = physicalState;
	}

	public Double getUnknownAcuteToxValues() {
		return unknownAcuteToxValues;
	}

	public void setUnknownAcuteToxValues(Double unknownAcuteToxValues) {
		this.unknownAcuteToxValues = unknownAcuteToxValues;
	}

	public List<MixtureComponent> getMixtureComponents() {
		return mixtureComponents;
	}

	public void setMixtureComponents(List<MixtureComponent> mixtureComponents) {
		this.mixtureComponents = mixtureComponents;
	}

	public List<MixtureHazard> getHazardsOfMixture() {
		return hazardsOfMixture;
	}

	public void setHazardsOfMixture(List<MixtureHazard> hazardsOfMixture) {
		this.hazardsOfMixture = hazardsOfMixture;
	}


	
	
	
}
