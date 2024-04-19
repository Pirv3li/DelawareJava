package repository;

import java.util.List;

import domein.Bestelling;
import jakarta.persistence.EntityNotFoundException;

public interface BestellingDao extends GenericDao<Bestelling> {
    public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException;   


}
