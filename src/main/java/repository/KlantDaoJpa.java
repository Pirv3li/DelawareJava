package repository;

import java.util.List;

import domein.Interface_Klant;
import domein.Klant;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class KlantDaoJpa extends GenericDaoJpa<Klant> implements KlantDao {
    
    public KlantDaoJpa() {
        super(Klant.class);
    }
    
    @Override
    public List<Klant> getKlantenByLeverancierID(int leverancierId) throws EntityNotFoundException {
        try {
            return em.createQuery("SELECT DISTINCT k FROM Klant k JOIN Bestelling b ON k.idKlant = b.idKlant WHERE b.idLeverancier = :idLeverancier", Klant.class)
                    .setParameter("idLeverancier", leverancierId)
                    .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }
    
    
    public Interface_Klant getKlantById(int klantId) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Klant.getKlantById", Klant.class)
                    .setParameter("idKlant", klantId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }

}
