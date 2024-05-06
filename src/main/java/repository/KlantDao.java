package repository;

import java.util.List;

import domein.Interface_Klant;
import domein.Klant;
import jakarta.persistence.EntityNotFoundException;

public interface KlantDao extends GenericDao<Klant> {
   
    List<Klant> getKlantenByLeverancierID(int leverancierId) throws EntityNotFoundException;
        
    Interface_Klant getKlantById(int klantId) throws EntityNotFoundException;
    
}
