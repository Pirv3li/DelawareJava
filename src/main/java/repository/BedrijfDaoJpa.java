package repository;

import domein.Bedrijf;
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
}
