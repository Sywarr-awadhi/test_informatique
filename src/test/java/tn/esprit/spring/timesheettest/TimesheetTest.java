package tn.esprit.spring.timesheettest;





import static org.junit.Assert.assertNotNull;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {
	
	
	
	
	private static final Logger l = LogManager.getLogger(TimesheetTest.class);
	
	@Autowired
	ITimesheetService timeSheetService;
	
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	IEmployeService employerService;
	
	
	
	
	
	@Test
	public void testAjouterTimeSheet() throws ParseException {
		Employe emp = new Employe(1, "bjaoui", "wael", "wael@g.com", true, Role.CHEF_DEPARTEMENT);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2021-05-08");
		Date dateFin = dateFormat.parse("2021-12-02");
		
		assertNotNull(employeRepository.save(emp).getId());
		try {
			l.info("In AjouterTimeSheet() : ");
			l.debug("lancer ajoutTimeSheet");
			timeSheetService.ajouterTimesheet(1, 1, dateDebut, dateFin);
			//employerService.deleteEmployeById(1);
		} catch (Exception e) {
			l.error("Erreur dans AjouterTimeSheet() :" + e);
		}

	}
	
	
	
	@Test
	public void testValiderTimeSheet() {
		try {
			l.info("In ValiderTimeSheet() : ");
			l.debug("lancer methode");
			timeSheetService.validerTimesheet(1, 1, new Date(), new Date("2021-09-12"), 1);
		} catch (Exception e) {
			l.error("Erreur dans ValiderTimeSheet() :" + e);
		}

	}
	
	
	
	
	
	
	
	
	

}
