package repository;

import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;

public interface LeverancierDao extends GenericDao<Leverancier>{
	
	public Leverancier getLeverancierByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;

	public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException;

	public void updateLeverancier(Leverancier lever) throws EntityNotFoundException;  
}
