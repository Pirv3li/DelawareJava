package repository;

import java.util.List;

import domein.Admin;
import domein.Bedrijf;
import domein.GoedKeuringLeverancier;
import domein.Interface_GoedKeuringLeverancier;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface AdminDao extends GenericDao<Admin>{
	
	//adminDao
	
	public Admin getAdminByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;   
	
	//bedrijfDao
	
	public List<Bedrijf> getBedrijven(int aantal, int begin) throws EntityNotFoundException;
	
	public void updateBedrijfByIdBedrijf(int idBedrijf, String iban, String btwNummer, String telefoonnummer, String sector, int idAdres) throws EntityNotFoundException;
	
	//goedKeuringDao
	
    public void keuringVeranderVerzoekenLeverancier(int id, String afgehandeld) throws EntityNotFoundException;  

    
    public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld, int aantal, int begin) throws EntityNotFoundException;

	//adresDao
	
	public int createAdres(String straat, String nummer, String stad, String postcode) throws EntityNotFoundException;   
	
	//leverancierDao
	
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email) throws EntityNotFoundException;

}
