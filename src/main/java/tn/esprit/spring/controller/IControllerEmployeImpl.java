package tn.esprit.spring.controller;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.dto.ContratDTO;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;


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
	//Departement
	public void affecterEmployeADepartement(int employeId, int depId) {
		iemployeservice.affecterEmployeADepartement(employeId, depId);
		
	}

	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		// TODO Auto-generated method stub
		return iemployeservice.getSalaireMoyenByDepartementId(departementId);
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
   //wissem
   
   @Autowired						
   
	ITimesheetService itimesheetservice;		
   public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return iemployeservice.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}
}
