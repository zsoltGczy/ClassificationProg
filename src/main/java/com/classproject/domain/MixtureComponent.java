package com.classproject.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity(name="Components")
public class MixtureComponent {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long componentId; 
	@NotEmpty(message = "Name must not be empty")
	@Size(min = 2, max = 32, message = "Name field must be between 2 and 32 characters long")
	private String componentName;
	@Pattern(regexp = "^(?:\\d{1,7}(-\\d{2})(-\\d{1}))?$", message = "Format of CAS-number is not correct")
	private String cas;
	@Pattern(regexp = "^(?:\\d{3}(-\\d{3})(-\\d{1}))?$", message = "Format of EC-number is not correct")
	private String ec;
	@NotEmpty(message = "Hazard(s) must be added")
	private ArrayList<String> hazards;
	@NotNull(message = "Concentration field must not be empty")
	@Range(min = 0, max = 100, message = "Concentration must be between 0 and 100")
	private Double concentration;
	@ManyToOne
	private Mixture mixture;
	@OneToMany(mappedBy = "mixtureComponent", cascade=CascadeType.ALL)
	private List<SpecificConcLimit> specificConcLimits;
	
	
    public MixtureComponent() {}
    
    public MixtureComponent(Mixture mixture) {
    	this.mixture = mixture;
    }
    

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public ArrayList<String> getHazards() {
		return hazards;
	}

	public void setHazards(ArrayList<String> hazards) {
		this.hazards = hazards;
	}

	public Double getConcentration() {
		return concentration;
	}

	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	public Mixture getMixture() {
		return mixture;
	}

	public void setMixture(Mixture mixture) {
		this.mixture = mixture;
	}

	public List<SpecificConcLimit> getSpecificConcLimits() {
		return specificConcLimits;
	}

	public void setSpecificConcLimits(List<SpecificConcLimit> specificConcLimits) {
		this.specificConcLimits = specificConcLimits;
	}

	


	
	
	
}