package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Employe;



public interface EmployeRepository extends CrudRepository<Employe, Integer>  {
	
	//SIWAR
	@Query("SELECT count(*) FROM Employe")
    public int countemp();
	
    @Query("SELECT nom FROM Employe")
    public List<String> employeNames();
    
    @Modifying
    @Transactional
    @Query("UPDATE Employe e SET e.email=:email1 where e.id=:employeId")
    public void mettreAjourEmailByEmployeIdJPQL(@Param("email1")String email, @Param("employeId")int employeId);

    @Query("select c.salaire from Contrat c join c.employe e where e.id=:employeId")
    public float getSalaireByEmployeIdJPQL(@Param("employeId")int employeId);
    
    
   
    
    
    //JIHEN
    @Modifying
    @Transactional
    @Query("DELETE from Contrat")
    public void deleteAllContratJPQL();

	public void deleteEmployeById(int i);

	public Employe getEmployeById(int employeId);

	 @Query("Select "
			+ "DISTINCT AVG(cont.salaire) from Contrat cont "
			+ "join cont.employe emp "
			+ "join emp.departements deps "
			+ "where deps.id=:depId")
    public Double getSalaireMoyenByDepartementId(@Param("depId")int departementId);
	
    	
    		
   

}
