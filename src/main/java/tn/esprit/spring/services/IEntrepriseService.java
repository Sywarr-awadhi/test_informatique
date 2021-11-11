package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public int ajouterEntreprise(Entreprise entreprise);
	public void deleteEntrepriseById(int entrepriseId);
	public Entreprise getEntrepriseById(int entrepriseId);
	
	//FIRAS MANSOUR
	public void affecterDepartementAEntreprise(int depId, int entrepriseId);
	public int ajouterDepartement(Departement dep);
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public void deleteDepartementById(int depId);
}
