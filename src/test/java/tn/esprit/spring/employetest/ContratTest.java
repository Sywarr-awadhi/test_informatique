package tn.esprit.spring.employetest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratTest {
    @MockBean
    ContratRepository contratRepository;
    @MockBean
    EmployeRepository employeRepository;
      @Autowired
	IEmployeService iemployeService;
    @Test
	public void testAjouterContrat() {
		   Contrat contrat=new Contrat();
		   contrat.setDateDebut(new Date());
		   contrat.setSalaire(100);
		   contrat.setReference(12);
		   contrat.setTypeContrat("CDD");
		  when(contratRepository.save(contrat)).thenReturn(contrat);	
		  assertEquals(contrat.getReference(), iemployeService.ajouterContrat(contrat));
	}
	@Test
	public void testDeleteContratById() {
		     Contrat contrat=new Contrat();
			   contrat.setDateDebut(new Date());
			   contrat.setSalaire(100);
			   contrat.setReference(1);
			   contrat.setTypeContrat("CDD"); 
			   Optional<Contrat> optionalEntityType = Optional.of(contrat);
		    when(contratRepository.findById(1)).thenReturn(optionalEntityType);
		   assertEquals("success Delete Contrat",iemployeService.deleteContratById(contrat.getReference()));
	}
	@Test
	public void testDeleteAllContratJPQL() {
		List<Contrat> c=iemployeService.deleteAllContratJPQL();
		   assertEquals(0,c.size());
	}
	@Test
	public void testAffectEmplToContrat() {
		   Contrat c = new Contrat();
		   c.setReference(4);
		   c.setDateDebut(new Date());
		   c.setSalaire(100);
		   c.setTypeContrat("CDD");
		   when(contratRepository.findById(4)).thenReturn(Optional.of(c));
		   Employe e = new Employe();
		   e.setActif(true);
		   e.setId(2);
		   e.setEmail("xx@gmail.com");
		   e.setNom("xx");
		   e.setPrenom("ben xxxx");
		   when(employeRepository.findById(2)).thenReturn(Optional.of(e));
		   when(contratRepository.save(c)).thenReturn(c);	
		   Contrat c1=iemployeService.affecterContratAEmploye(c.getReference(),e.getId());
		   assertEquals(c.getEmploye().getId(),c1.getEmploye().getId());
	}
}
