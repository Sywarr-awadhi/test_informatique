package tn.esprit.spring.services;


import java.util.List;
import java.util.Date;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;



public interface IEmployeService {
    //SIWAR
	public int ajouterEmploye(Employe employe);
	public String mettreAjourEmailByEmployeId(String email, int employeId);
	public String getEmployePrenomById(int employeId);
	public void deleteEmployeById(int employeId);
	public int getNombreEmployeJPQL();
	public List<String> getAllEmployeNamesJPQL();
	public float getSalaireByEmployeIdJPQL(int employeId);
	public String mettreAjourEmailByEmployeIdJPQL(String email, int employeId);
	public List<Employe> getAllEmployes();
	public Integer addOrUpdateEmploye(Employe employe);
	public Employe getEmployeById(int id);
	public String deleteEmploye(int id);
	

	

	
	//Jihen
	public String deleteContratById(int contratId);
	public int ajouterContrat(Contrat contrat);
	public List<Contrat> deleteAllContratJPQL();
	public Contrat affecterContratAEmploye(int contratId, int employeId);

	// FIRAS MANSOUR
	public void affecterEmployeADepartement(int employeId, int depId);
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public Double getSalaireMoyenByDepartementId(int departementId);
	
	//wissem
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
			Date dateDebut, Date dateFin);
}
