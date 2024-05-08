package repository;

import domein.Interface_Leverancier;
import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class LeverancierDaoJpa extends GenericDaoJpa<Leverancier> implements LeverancierDao {
	public LeverancierDaoJpa() {
		super(Leverancier.class);
	}

	@Override
	public Leverancier getLeverancierByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierByGebruikersnaam", Leverancier.class)
					.setParameter("gebruikersnaam", gebruikersnaam).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
	
	
	public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierByIdBedrijf", Leverancier.class)
					.setParameter("idBedrijf", idBedrijf).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
	
	public Leverancier getLeverancierById(int idLeverancier) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierById", Leverancier.class)
					.setParameter("idLeverancier", idLeverancier).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
	
	public void updateLeverancier(Interface_Leverancier lever) throws EntityNotFoundException {
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
	        transaction.begin();
	        
	        Query query = em.createNamedQuery("Leverancier.updateLeverancierBetaalMethodes");
	        query.setParameter("betaalMethodes", lever.getBetaalMethodes());
	        query.setParameter("idLeverancier", lever.getIdLeverancier());
	        
	        int updatedEntities = query.executeUpdate();
	        
	        if (updatedEntities == 0) {
	            throw new EntityNotFoundException();
	        }
	        
	        transaction.commit();
	    } catch (Exception ex) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	}


	}
