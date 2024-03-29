package tn.esprit.spring.controller;

import java.util.List;
import java.util.Date;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.dto.ContratDTO;
import tn.esprit.spring.dto.EmployeDTO;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;

@RestController

public class RestControlEmploye {
	static final  Logger logger = Logger.getLogger(RestControlEmploye.class);
	@Autowired
	IEmployeService iemployeservice;
	  @Autowired
	    private ModelMapper modelMapper;
	//Departement
	  // http://localhost:8081/SpringMVC/servlet/affecterEmployeADepartement/1/1
		@PutMapping(value = "/affecterEmployeADepartement/{idemp}/{iddept}") 
		public void affecterEmployeADepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId) {
			iemployeservice.affecterEmployeADepartement(employeId, depId);
			
		}           
		// http://localhost:8081/SpringMVC/servlet/desaffecterEmployeDuDepartement/1/1
		@PutMapping(value = "/desaffecterEmployeDuDepartement/{idemp}/{iddept}") 
		public void desaffecterEmployeDuDepartement(@PathVariable("idemp")int employeId, @PathVariable("iddept")int depId)
		{
			iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
		}
		
		// URL : http://localhost:8081/SpringMVC/servlet/getSalaireMoyenByDepartementId/2
	    @GetMapping(value = "getSalaireMoyenByDepartementId/{iddept}")
	    @ResponseBody
		public Double getSalaireMoyenByDepartementId(@PathVariable("iddept")int departementId) {
			return iemployeservice.getSalaireMoyenByDepartementId(departementId);
		}

	//SIWAR
	
	// http://localhost:8081/SpringMVC/servlet/ajouterEmployer
		
		
	  @PostMapping("/ajouterEmployer")
		@ResponseBody
		public int ajouterEmploye(@RequestBody EmployeDTO employeDTO)
		{
		  Employe employe = modelMapper.map(employeDTO,Employe.class);
			return iemployeservice.ajouterEmploye(employe);
			
		}

	  
		
		
		
		
		
		
		
		
		
		// Modifier email : http://localhost:8081/SpringMVC/servlet/modifyEmail/1/newemail
		@PutMapping(value = "/modifyEmail/{id}/{newemail}") 
		@ResponseBody
		public void mettreAjourEmailByEmployeId(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {
			iemployeservice.mettreAjourEmailByEmployeId(email, employeId);
			
		}

		   // URL : http://localhost:8081/SpringMVC/servlet/getEmployePrenomById/2
		   @GetMapping(value = "getEmployePrenomById/{idemp}")
		   @ResponseBody
		   public String getEmployePrenomById(@PathVariable("idemp")int employeId) {
				return iemployeservice.getEmployePrenomById(employeId);
			}
		   
		   
		   /**
		   // URL : http://localhost:8081/SpringMVC/servlet/deleteEmployeById/1
		    @DeleteMapping("/deleteEmployeById/{idemp}") 
			@ResponseBody 
			public void deleteEmployeById(@PathVariable("idemp")int employeId) {
		    	
				iemployeservice.deleteEmployeById(employeId);
				
			}
		 */
		    @DeleteMapping("/deleteEmployeById/{idemp}") 
			@ResponseBody 
			public void deleteEmployeById(@PathVariable("idemp")int employeId) {
		    	
				iemployeservice.deleteEmploye(employeId);
				
			}
		
		    // Modifier email : http://localhost:8081/SpringMVC/servlet/mettreAjourEmailByEmployeIdJPQL/2/newemail
		 	@PutMapping(value = "/mettreAjourEmailByEmployeIdJPQL/{id}/{newemail}") 
		 	@ResponseBody
			public void mettreAjourEmailByEmployeIdJPQL(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {	
			iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);
				
			}
	
		 // URL : http://localhost:8081/SpringMVC/servlet/getNombreEmployeJPQL
		    @GetMapping(value = "getNombreEmployeJPQL")
		    @ResponseBody
			public int getNombreEmployeJPQL() {
				
				return iemployeservice.getNombreEmployeJPQL();
			}

		    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeNamesJPQL
		    @GetMapping(value = "getAllEmployeNamesJPQL")
		    @ResponseBody
			public List<String> getAllEmployeNamesJPQL() {
				
				return iemployeservice.getAllEmployeNamesJPQL();
			}
		    // URL : http://localhost:8081/SpringMVC/servlet/getSalaireByEmployeIdJPQL/2
		    @GetMapping(value = "getSalaireByEmployeIdJPQL/{idemp}")
		    @ResponseBody
			public float getSalaireByEmployeIdJPQL(@PathVariable("idemp")int employeId) {
				return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
			}
		    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployes
			@GetMapping(value = "/getAllEmployes")
		    @ResponseBody
			public List<Employe> getAllEmployes() {
				
				return iemployeservice.getAllEmployes();
			}

	
	
	
	
	
	
	//JIHEN
	// http://localhost:8081/SpringMVC/servlet/ajouterContrat
	@PostMapping("/ajouterContrat")
	@ResponseBody
		public int ajouterContrat(@RequestBody ContratDTO contrat) {
			Contrat persitentcontrat=new Contrat(contrat.getDateDebutDto(), contrat.getTypeContratDto(), contrat.getSalaireDto(),contrat.getEmployeDto());
			iemployeservice.ajouterContrat(persitentcontrat);
			return persitentcontrat.getReference();
	}
	
	

 // URL : http://localhost:8081/SpringMVC/servlet/deleteContratById/2
    @DeleteMapping("/deleteContratById/{idcontrat}") 
	@ResponseBody
	public void deleteContratById(@PathVariable("idcontrat")int contratId) {
		iemployeservice.deleteContratById(contratId);
	}
 // http://localhost:8081/SpringMVC/servlet/affecterContratAEmploye/6/1
    @PutMapping(value = "/affecterContratAEmploye/{idcontrat}/{idemp}") 
 	public void affecterContratAEmploye(@PathVariable("idcontrat")int contratId, @PathVariable("idemp")int employeId)
 	{
 		iemployeservice.affecterContratAEmploye(contratId, employeId);
 	}
    // URL : http://localhost:8081/SpringMVC/servlet/deleteAllContratJPQL
    @DeleteMapping("/deleteAllContratJPQL") 
	@ResponseBody
	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();
		
	}

 //wissem
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return iemployeservice.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}
}
