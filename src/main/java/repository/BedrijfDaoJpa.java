package repository;

import java.util.List;

import domein.Bedrijf;
import domein.Klant;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class BedrijfDaoJpa extends GenericDaoJpa<Bedrijf> implements BedrijfDao  {
    public BedrijfDaoJpa() {
        super(Bedrijf.class);
    }

    @Override
    public Bedrijf getBedrijfById(int id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bedrijf.getBedrijfById", Bedrijf.class)
                 .setParameter("idBedrijf", id)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    public List<Bedrijf> getBedrijven() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bedrijf.getBedrijven", Bedrijf.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }

	public Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException {
		try {
			return em.createQuery("SELECT b FROM Klant k JOIN Bedrijf b ON k.idBedrijf = b.idBedrijf WHERE k.idKlant = :idKlant", Bedrijf.class)
                    .setParameter("idKlant", id)
                    .getSingleResult();
		} catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}
}
