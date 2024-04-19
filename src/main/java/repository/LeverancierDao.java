package repository;

import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;

public interface LeverancierDao extends GenericDao<Leverancier>{
	
	public Leverancier getLeverancierByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;  
}
