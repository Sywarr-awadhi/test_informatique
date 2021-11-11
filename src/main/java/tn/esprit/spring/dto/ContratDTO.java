package tn.esprit.spring.dto;

import java.util.Date;

import tn.esprit.spring.entities.Employe;

public class ContratDTO {
	private int referenceDto;
	
	private Date dateDebutDto;
	
	private String typeContratDto;
	
	private float salaireDto;
	
	private Employe employeDto;

	public ContratDTO() {
		super();
	}

	public int getReferenceDto() {
		return referenceDto;
	}

	public void setReferenceDto(int referenceDto) {
		this.referenceDto = referenceDto;
	}

	public Date getDateDebutDto() {
		return dateDebutDto;
	}

	public void setDateDebutDto(Date dateDebutDto) {
		this.dateDebutDto = dateDebutDto;
	}

	public String getTypeContratDto() {
		return typeContratDto;
	}

	public void setTypeContratDto(String typeContratDto) {
		this.typeContratDto = typeContratDto;
	}

	public float getSalaireDto() {
		return salaireDto;
	}

	public void setSalaireDto(float salaireDto) {
		this.salaireDto = salaireDto;
	}

	public Employe getEmployeDto() {
		return employeDto;
	}

	public void setEmployeDto(Employe employeDto) {
		this.employeDto = employeDto;
	}

	
}
