package repository;

import java.util.List;

import domein.Bestelling;
import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface BestellingDao extends GenericDao<Bestelling> {
    public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException;   

    public ObservableList<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException;   

    public void veranderBetalingStatus(String id, Boolean betalingStatus) throws EntityNotFoundException;  
    
    
}
