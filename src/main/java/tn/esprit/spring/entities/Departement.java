package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Departement implements Serializable {

	private static final long serialVersionUID = -357738161698377833L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String  name ; 
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	//@JsonManagedReference 
	@JsonIgnore
	
	@ManyToMany
	private List<Employe> employes;
	@ManyToOne
	private Entreprise entreprise;


	
	public Entreprise getEntreprise() {
		return entreprise;
	}



	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}







	public Departement() {
		super();
	}
	
	

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	
	
	

}
