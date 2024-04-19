package repository;

import java.util.List;

import domein.BestellingDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class BestellingDetailsDaoJpa extends GenericDaoJpa<BestellingDetails> implements BestellingDetailsDao{
	
	public BestellingDetailsDaoJpa() {
        super(BestellingDetails.class);
    }

    @Override
    public List<BestellingDetails> getBestellingDetailsByOrderId(String id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("BestellingDetails.getBestellingDetailsByOrderId", BestellingDetails.class)
                 .setParameter("idOrder", id)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
