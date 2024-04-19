package repository;

import java.util.List;

import domein.BestellingDetails;
import jakarta.persistence.EntityNotFoundException;

public interface BestellingDetailsDao extends GenericDao<BestellingDetails>{
    public List<BestellingDetails> getBestellingDetailsByOrderId(String id) throws EntityNotFoundException;   

}
