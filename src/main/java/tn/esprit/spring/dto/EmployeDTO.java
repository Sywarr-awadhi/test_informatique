package tn.esprit.spring.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Role;



public class EmployeDTO  {
	
	private int iDDto;
	private String prenomDTO; 
	private String nomDTO;
	public String getNomDTO() {
		return nomDTO;
	}

	public void setNomDTO(String nomDTO) {
		this.nomDTO = nomDTO;
	}

	private String emailDTO;
	private boolean isActifDTO;
	@Enumerated(EnumType.STRING)
	private Role roleDTO;
	
	private Contrat contratDTO;

	public EmployeDTO() {
		super();
		}

	public int getiDDto() {
		return iDDto;
	}

	public void setiDDto(int iDDto) {
		this.iDDto = iDDto;
	}

	public String getPrenomDTO() {
		return prenomDTO;
	}

	public void setPrenomDTO(String prenomDTO) {
		this.prenomDTO = prenomDTO;
	}

	public String getEmailDTO() {
		return emailDTO;
	}

	public void setEmailDTO(String emailDTO) {
		this.emailDTO = emailDTO;
	}

	public boolean isActifDTO() {
		return isActifDTO;
	}

	public void setActifDTO(boolean isActifDTO) {
		this.isActifDTO = isActifDTO;
	}

	public Role getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(Role roleDTO) {
		this.roleDTO = roleDTO;
	}

	public Contrat getContratDTO() {
		return contratDTO;
	}

	public void setContratDTO(Contrat contratDTO) {
		this.contratDTO = contratDTO;
	}

	

}
