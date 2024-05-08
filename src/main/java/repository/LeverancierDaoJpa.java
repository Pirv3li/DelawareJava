package repository;

import domein.Bedrijf;
import domein.Interface_Leverancier;
import domein.Leverancier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class LeverancierDaoJpa extends GenericDaoJpa<Leverancier> implements LeverancierDao {
	
	
    @PersistenceContext
    private EntityManager entityManager;
    
    
    public LeverancierDaoJpa() {
        super(Leverancier.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("b2bDeleware");
        this.entityManager = emf.createEntityManager();
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

	@Override
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email) {
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Query query = em.createNamedQuery("Leverancier.updateLeverancierById");
			query.setParameter("idLeverancier", idLeverancier);
			query.setParameter("gebruikersnaam", gebruikersnaam);
			query.setParameter("email", email);


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
	
	@Override
	public void updateBedrijfByLeverancier(Leverancier leverancier, Bedrijf bedrijf) throws EntityNotFoundException {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Leverancier managedLeverancier = entityManager.find(Leverancier.class, leverancier.getIdLeverancier());
			if (managedLeverancier == null) {
				throw new EntityNotFoundException();
			}
			managedLeverancier.setBedrijf(bedrijf);
			entityManager.merge(managedLeverancier);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}


}
