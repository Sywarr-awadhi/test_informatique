package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.exception.ContratNotFoundException;
import tn.esprit.spring.exception.EmployeNotFoundException;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	
	 private static final Logger logger = LogManager.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	
	@Autowired
	ContratRepository contratRepoistory;
	
	@Autowired
	DepartementRepository deptRepoistory;
	
	
	//Departement
	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}
	
	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	//SIWAR
	
	public int ajouterEmploye(Employe employe) {
		logger.info("Lancement de l'ajout de l'employé à la base de données");
		try { employeRepository.save(employe);
		     logger.info("Succés de l'ajout de l'employé");
		}
		catch (Exception e){
			logger.error("Un erreur a apparait lors de l'ajout de l'employé");
			}
		return employe.getId();
	}
	
	public Integer addOrUpdateEmploye(Employe employe) {
		logger.debug("Methode ajouterEmployee");
		try {
			employeRepository.save(employe);
			logger.info("Employe ajoutée avec id = "+employe.getId());
		return employe.getId();
		}
		catch (Exception e) {
			logger.error("erreur methode ajouterEntreprise :" +e);	
		       return null;       
				}		
	}
	
	
	
	
	public String mettreAjourEmailByEmployeId(String email, int employeId) {
		String msg="";
		Employe x = new Employe ();
		try {
		logger.info("employe existe");	
		logger.debug("mis a jour mail");
		Optional<Employe> y = employeRepository.findById(employeId) ;
		if (y.isPresent())
		{
			x = y.get();
		}
		
	    
		x.setEmail(email);
		logger.info("mis a jour mail avec Succès");
		msg="success";
	
		employeRepository.save(x);
		logger.info("mis a jour sans erreur");
		}catch (Exception e) {
			logger.error("Erreur avec la  mis a jour   email " +e);
			msg="c est un erreur";
		}
		return msg;
	}

	
	
		

	
	public String getEmployePrenomById1(int employeId) {
		logger.debug("lancement de la methode getEmplpyeById ");
		try {
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		logger.debug("la méthode getEmployeById est finie avec succés ");
		
		return employeManagedEntity!=null ?employeManagedEntity.getPrenom():null;}
		catch (Exception e) {
			logger.error("erreur methode getEmployeeById : " +e);
			return null;
		}	
		
	}
//test 
	public String getEmployePrenomById(int employeId) {
		
		Employe x = new Employe();
		try{
			logger.info("affichage d'une employe par id : "+employeId);
			logger.debug("entrain d'afficher employe ... ");
			
			
			Optional<Employe> y = employeRepository.findById(employeId) ;
			if (y.isPresent())
			{
				x = y.get();
			}
								
			logger.debug("je viens d'afficher employe: ");
			logger.info("affichage sans erreurs " );
		}
		catch(Exception e){
			logger.error("Erreur dans l'affichage de employe: "+e);
		}finally{
			logger.info("Methode affichage");
	
	
	
		}
	return x.getPrenom();
}

	
	@Override
	public String deleteEmploye(int id) {
		String msg="";
		try{
			logger.info("Finding Employe with id = %d"+id);
			employeRepository.deleteById(id);
			logger.info("Employe Deleted Successfuly ");
			msg="Delete Done";
		}catch (Exception e) {

			logger.error("The emp with id = %d does not Exist"+id);
			msg="error";
		}
   return msg;
	}
	
	
	// à retester
	
    public void deleteEmployeById(int employeId)
	
	 { 
		Employe employe = new Employe ();
		try {
		logger.info("suppression d'un employe ");
		logger.debug("selection du emoloye a supprimé");
		
		Optional<Employe> y = employeRepository.findById(employeId) ;
		if (y.isPresent())
		{
		
			
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}
		}
		
		

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		
		logger.debug("suppression de employe ");
		employeRepository.delete(employe);
		logger.debug("je viens de supprimer un employe");
		logger.info("suppression without errors");
	}
		
	catch(Exception e){
		logger.error("Erreur dans l'affectation du employe : "+e);
	}
	}

	
	
	
	
	@Override
	public Employe getEmployeById(int id) {
		Optional<Employe> employe = employeRepository.findById(id);
        if (employe.isPresent()) {
        	if(logger.isDebugEnabled())
        	{
        		logger.debug(String.format("Entreprise exitse: %d", employe.get().getId()));        		
        	}
            return employe.get();
        }
        return null;
	}
	
public List<Employe> getAllEmployes() {
		
		List<Employe> employes = null; 
		try {
	
			
			logger.info("In Method getAllEmployes");
			employes = (List<Employe>) employeRepository.findAll();
				 logger.info("Employes");
					logger.debug("Connexion Bd");
					
					for (Employe employe : employes) {
						logger.info("Employe"+employe.getNom());
					} 
					logger.info("out with succes");

				}catch (Exception e) {
					logger.error("out of method with error"+e);		}

				return employes;
			}


	   /**
		public void deleteEmployeById(int employeId)throws NoSuchElementException
		{
			Employe employe = employeRepository.findById(employeId).get();

			//Desaffecter l'employe de tous les departements
			//c'est le bout master qui permet de mettre a jour
			//la table d'association
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		}
    
    /**	@Transactional
	public int deleteEmployeById(int employeId)
	{
		 logger.info("lancement de la méthode de la suppression de l'id de l'employé de la base de données ");
		 try {
			 if (employeRepository.findById(employeId).orElse(null)!=null){
				 employeRepository.delete(employeRepository).findById(employeId).orElse(null));
				 logger.debug("deleteEntrepriseById fini avec succes ");
				 
				 return 0;}else {
					 logger.error("erreur methode deleteEntrepriseById : " );
						return -1;
					}
				} catch (Exception e) {
					logger.error("erreur methode deleteEntrepriseById : " +e);
					return -1;
					}
				}		
	*/


	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	
	public String mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		String msg="&&";
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		return msg;

	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	
	

	//JIHEN 
			public List<Contrat> deleteAllContratJPQL() {
				 logger.info("debut suppression de tous les contrat");
				 try{ 
					 employeRepository.deleteAllContratJPQL();
					 logger.info("fin ppression de tous les contrat avec succées");
					 }
				 catch(Exception e){
					 logger.error("error lors de la suppression de tous les contrats");
				 	}
				 return (List<Contrat>) contratRepoistory.findAll();
			}

			public String deleteContratById(int contratId) {
				String c="";
				   logger.info("debut suppression contrat");
				Contrat contratManagedEntity=new Contrat();
				  try{	contratManagedEntity = contratRepoistory.findById(contratId).orElseThrow(()-> new ContratNotFoundException("contrat inexistable"));
						 logger.trace("reference contrat "+contratManagedEntity.getReference());
					  }
				  catch(Exception e){
							logger.error("contrat invalide");
					  }
				  try{	
					  contratRepoistory.delete(contratManagedEntity);
					  c="success Delete Contrat";
					  logger.info("la surpression est realise avec succés");
						}
					catch(Exception e){			
						logger.error("erreur lors de la suppression");
						c="error Delete contrat";
					   }
				  return c;
			}
			public Contrat affecterContratAEmploye(int contratId, int employeId) {
					Contrat c=new Contrat();
				  	logger.info("debut affectation contrat a employe");   
				  	Contrat contratManagedEntity=new Contrat();	
				  	Employe employeManagedEntity=new Employe(); 
				   try{
					 contratManagedEntity =contratRepoistory.findById(contratId).orElseThrow(()->new ContratNotFoundException("Contrat inexistable"));
					 logger.trace("reference contrat"+contratManagedEntity.getReference());
				   		}
				   
				   catch(Exception e){
						logger.error("contrat invalide");
				   		}
				   
				   try{		
					   employeManagedEntity = employeRepository.findById(employeId).orElseThrow(()->new EmployeNotFoundException("Employe inexistable"));
					   logger.trace("nom de l employe a affecter"+employeManagedEntity.getNom());	
				   		}
				   
				   catch(Exception e){			
					   logger.error("employe invalide");
					   }
				   
					try{		
						contratManagedEntity.setEmploye(employeManagedEntity);
						
						c=contratRepoistory.save(contratManagedEntity);
					  	logger.info("l'affectation est realise avec succés");
						}
					catch(Exception e){			
						logger.error("erreur lors de l affectation");
					   }
				  	return c;
			}

				public int ajouterContrat(Contrat contrat) {
					   logger.info("debut methode ajout");	  
					   try{	 contratRepoistory.save(contrat);
							 logger.info("l'ajout est realise avec succés, fin methode ajouterContarat");
						   		}
						   catch(Exception e){
								logger.error("erreur d'ajout");}
					return contrat.getReference();
				}
				
			//wissem
				
			@Autowired
			TimesheetRepository timesheetRepository;
			
			public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
					Date dateFin) {
				return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
			}

				
				
	}
