package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;



public interface ITimesheetService {
	
	
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
			Date dateDebut, Date dateFin);
	public void affecterMissionADepartement(int missionId, int depId);
	
	//wissem
	public int ajouterMission(Mission mission);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
}
