package tn.esprit.spring.entreprisetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.employetest.ContratTest;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;


import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseTest {
	
	private static final Logger logger = LogManager.getLogger(ContratTest.class);
    @MockBean
    EntrepriseRepository entrepriseRepository;
    
    @Autowired
	IEntrepriseService ientrepriseService;
    
    
    
    @Test
	public void testajouterEntreprise() {
		   logger.info("debut test ajout");	  
		   Entreprise entreprise=new Entreprise();
		   entreprise.setId(1);
		   entreprise.setName("entreprise");
		   entreprise.setRaisonSocial("raison social");
		   
		   logger.trace("affichage id entreprise à ajouter: " + entreprise.toString());
		  when(entrepriseRepository.save(entreprise)).thenReturn(entreprise);	
		  logger.trace("affichage id entreprise existe dans repo mock: "+ ientrepriseService.ajouterEntreprise(entreprise));
		  assertEquals(entreprise.getId(), ientrepriseService.ajouterEntreprise(entreprise));
		   logger.info(" test ajout entreprise terminé");	  
	}
	@Test
	public void testdeleteEntrepriseById() {
		   logger.info("debut test suppression par id ");	
		   
		   Entreprise entreprise=new Entreprise();
		   entreprise.setId(1);
		   entreprise.setName("entreprise");
		   entreprise.setRaisonSocial("raison social"); 
			   Optional<Entreprise> optionalEntityType = Optional.of(entreprise);
		    when(entrepriseRepository.findById(1)).thenReturn(optionalEntityType);
		   ientrepriseService.deleteEntrepriseById(entreprise.getId());
		  verify(entrepriseRepository,times(1)).delete(entreprise);
		   logger.info("fin test suppression entreprise avec succées");	
	}
	
	
	@Test
	public void getEntrepriseByIdTest() {
		logger.info("debut test affichage par id ");
		
		Entreprise entreprise=new Entreprise();
		   entreprise.setId(1);
		   entreprise.setName("entreprise");
		   entreprise.setRaisonSocial("raison social"); 
			   Optional<Entreprise> optionalEntityType = Optional.of(entreprise);
		    when(entrepriseRepository.findById(1)).thenReturn(optionalEntityType);
		    ientrepriseService.getEntrepriseById(entreprise.getId());
		  
		    logger.info("fin test affichage entreprise avec succées");
		
	}

}
