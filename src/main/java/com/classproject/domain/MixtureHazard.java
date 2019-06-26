package com.classproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Hazards")
public class MixtureHazard {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String pictogram;
	private String signalWord;
	private String hazardStatement;
	private String precautionaryStatement;
	@ManyToOne
	private Mixture mixture;
	
	
	
	public MixtureHazard() {}
	
	public MixtureHazard(String name, String pictogram, String signalWord, String hazardStatement,
			String precautionaryStatement, Mixture mixture) {
		this.name = name;
		this.pictogram = pictogram;
		this.signalWord = signalWord;
		this.hazardStatement = hazardStatement;
		this.precautionaryStatement = precautionaryStatement;
		this.mixture = mixture;
	}


	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPictogram() {
		return pictogram;
	}
	public void setPictogram(String pictogram) {
		this.pictogram = pictogram;
	}
	public String getSignalWord() {
		return signalWord;
	}
	public void setSignalWord(String signalWord) {
		this.signalWord = signalWord;
	}
	public String getHazardStatement() {
		return hazardStatement;
	}
	public void setHazardStatement(String hazardStatement) {
		this.hazardStatement = hazardStatement;
	}
	public String getPrecautionaryStatement() {
		return precautionaryStatement;
	}
	public void setPrecautionaryStatement(String precautionaryStatement) {
		this.precautionaryStatement = precautionaryStatement;
	}
	public Mixture getMixture() {
		return mixture;
	}
	public void setMixture(Mixture mixture) {
		this.mixture = mixture;
	}
	
}
