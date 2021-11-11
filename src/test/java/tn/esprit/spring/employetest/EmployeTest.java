package tn.esprit.spring.employetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.times;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import static org.mockito.Mockito.verify;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.mockito.InjectMocks;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {
	private static final Logger logger = LogManager.getLogger(EmployeTest.class);
	 @Autowired
	 IEmployeService iemployeService;
     @MockBean
	 EmployeRepository employeRepo;
     @Autowired
     @InjectMocks
  	 private EmployeServiceImpl employeService;
     private Employe employe1;
	 private static String mail = "siwar.awadhi1@esprit.tn";
 
	 @Before
		public void setUp() {
			
			employe1 = new Employe(1,"awadhi", "siwar", "siwar.awadhi1@esprit.tn", true, Role.INGENIEUR);
		
			employeRepo.save(employe1);



		}

		@After
		public void tearDown() {
			
			employeRepo.deleteAll();
		}
   
     @Test
 	public void TestajouterEmploye() {
		   Employe e = new Employe();
		   e.setActif(true);
		   e.setEmail("swayer@gmail.com");
		   e.setNom("awadhi");
		   e.setPrenom("siwar"); 
		  when(employeRepo.save(e)).thenReturn(e);	
		  assertEquals(e.getId(), employeService.ajouterEmploye(e));
		  
	}
	  

 
 	 @Test
     public void TestgetAllEmployes() {
         assertTrue(employeService.getAllEmployes().isEmpty());
     }

   
 	
 	
 	@Test
	public void TestDeleteEmploye() {
		Employe emp = new Employe();
		assertNotNull(emp);
		employeRepo.deleteEmployeById(16);
				
	}
 	@Test
    public void TestgetSalaireByEmployeIdJPQL() {
        int employeId = 2;
        double salaire = employeService.getSalaireByEmployeIdJPQL(employeId);
        assertFalse(employeRepo.findById(employeId).isPresent() &&
        		employeRepo.findById(employeId).get().getContrat().getSalaire() == salaire);
    }
 	@Test
    public void TestgetAllEmployeNamesJPQL() {
        List<String> list = employeService.getAllEmployeNamesJPQL();
        List<String> list1 = employeService.getAllEmployes().stream().map(Employe::getNom).collect(Collectors.toList());
        assertEquals(list, list1);
    }
 	 @Test
     public void TestgetNombreEmployeJPQL() {
         List<Employe> employeList = employeService.getAllEmployes();
         int nb = employeService.getNombreEmployeJPQL();
         assertEquals(employeList.size(), nb);
     }
 	 
 	@Test
	public void TestmettreAjourEmailByEmployeId() {
		employeService.mettreAjourEmailByEmployeId(mail, employe1.getId());
		Optional<Employe> e = employeRepo.findById(employe1.getId());
		if (e.isPresent()) {
			assertThat(e.get().getEmail()).isEqualTo(mail);
		}
	}
 	@Test
	public void TestgetEmployePrenomById() {
		String prenom = employeService.getEmployePrenomById(employe1.getId());
	
		assertThat(prenom).isNull();
	}
 	@Test
	public void TestmettreAjourEmailByEmployeIdJPQL() {
		employeService.mettreAjourEmailByEmployeIdJPQL("siwar.awadhi1@esprit.tn", employe1.getId());
		Optional<Employe> e = employeRepo.findById(employe1.getId());
		if (e.isPresent()) {
			assertThat(e.get().getEmail()).isEqualTo("siwar.awadhi1@esprit.tn");
		}
	}
}




