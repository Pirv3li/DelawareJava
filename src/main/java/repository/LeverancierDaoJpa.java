package repository;

import domein.Leverancier;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

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


	}
