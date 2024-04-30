package repository;

import java.util.List;

import domein.Bestelling;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class BestellingDaoJpa extends GenericDaoJpa<Bestelling> implements BestellingDao{

	public BestellingDaoJpa() {
        super(Bestelling.class);
    }

    @Override
    public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bestelling.getBestellingenByLeverancierId", Bestelling.class)
                 .setParameter("idLeverancier", id)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }

	@Override
	public List<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException {
		 try {
	            return em.createNamedQuery("Bestelling.getBestellingenByKlantId", Bestelling.class)
	                 .setParameter("idKlant", id)
	                .getResultList();
	        } catch (NoResultException ex) {
	            throw new EntityNotFoundException();
	        } 
	}
}
