package tn.esprit.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.dto.ContratDTO;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IEmployeService;


@Controller
public class IControllerEmployeImpl  {
	@Autowired
	IEmployeService iemployeservice;
	//JIHEN
	public int ajouterContrat(ContratDTO contrat) {
		Contrat persitentcontrat=new Contrat(contrat.getDateDebutDto(), contrat.getTypeContratDto(), contrat.getSalaireDto(),contrat.getEmployeDto());
		iemployeservice.ajouterContrat(persitentcontrat);
		return persitentcontrat.getReference();
	}
	
	
	public void deleteContratById(int contratId) {
		iemployeservice.deleteContratById(contratId);
	}
	public void affecterContratAEmploye(int contratId, int employeId)
	{
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}

	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();
		
	}

	
	

	//SIWAR
	public int ajouterEmploye(Employe employe)
	{
		iemployeservice.ajouterEmploye(employe);
		return employe.getId();
	}
    
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);
		
	}
	public String getEmployePrenomById(int employeId) {
		return iemployeservice.getEmployePrenomById(employeId);
	}

	
	public void deleteEmployeById(int employeId) {
		iemployeservice.deleteEmployeById(employeId);
		
	}
    public List<String> getAllEmployeNamesJPQL() {
		
		return iemployeservice.getAllEmployeNamesJPQL();
	}

   public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {	
	iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		
	}

   public float getSalaireByEmployeIdJPQL(int employeId) {
	return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
}

   public List<Employe> getAllEmployes() {
	
	return iemployeservice.getAllEmployes();
}
}
