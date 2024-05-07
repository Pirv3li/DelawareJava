package repository;

import domein.GoedKeuringLeverancier;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface GoedKeuringLeverancierDao extends GenericDao<GoedKeuringLeverancier>{
	
    public void keuringVeranderVerzoekenLeverancier(String id, String afgehandeld) throws EntityNotFoundException;  

    
    public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld) throws EntityNotFoundException;
}
