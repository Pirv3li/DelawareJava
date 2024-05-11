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
	
	public List<Bedrijf> getBedrijven() throws EntityNotFoundException;
	
	//goedKeuringDao
	
    public void keuringVeranderVerzoekenLeverancier(String id, String afgehandeld) throws EntityNotFoundException;  

    
    public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld) throws EntityNotFoundException;

}
