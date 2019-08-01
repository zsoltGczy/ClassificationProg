package com.classproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity(name = "spec")
public class SpecificConcLimit {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long specId;
	@NotEmpty(message = "Name must not be empty")
	private String name;
	@NotNull(message = "Concentration field must not be empty")
	@Range(min = 0, max = 100, message = "Concentration must be between 0 and 100")
	private Double value;
	@ManyToOne
	private MixtureComponent mixtureComponent;
	
	
	public SpecificConcLimit() {}
	
	public SpecificConcLimit(MixtureComponent mixtureComponent) {
		
		this.mixtureComponent = mixtureComponent;
	}
	
	public SpecificConcLimit(String name, Double value) {
		
		this.name = name;
		this.value = value;
	}
	
	
	
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public MixtureComponent getMixtureComponent() {
		return mixtureComponent;
	}
	public void setMixtureComponent(MixtureComponent mixtureComponent) {
		this.mixtureComponent = mixtureComponent;
	}
	
	
}
