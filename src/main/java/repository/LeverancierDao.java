package repository;

import domein.Interface_Leverancier;
import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;

public interface LeverancierDao extends GenericDao<Leverancier>{
	
	public Leverancier getLeverancierByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;

	public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException;
	
	public Leverancier getLeverancierById(int idLeverancier) throws EntityNotFoundException;


	public void updateLeverancier(Interface_Leverancier lever) throws EntityNotFoundException;  
}
