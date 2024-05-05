package repository;

import java.util.List;

import domein.Bestelling;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class BestellingDaoJpa extends GenericDaoJpa<Bestelling> implements BestellingDao {

	public BestellingDaoJpa() {
		super(Bestelling.class);
	}

	@Override
	public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Bestelling.getBestellingenByLeverancierId", Bestelling.class)
					.setParameter("idLeverancier", id).getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public ObservableList<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException {
		try {
			List<Bestelling> resultList = em.createNamedQuery("Bestelling.getBestellingenByKlantId", Bestelling.class)
					.setParameter("idKlant", id).getResultList();
			return FXCollections.observableList(resultList);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	public void veranderBetalingStatus(String id, Boolean betalingStatus) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		boolean hulp = false;
		if (betalingStatus) {
			hulp = false;
		} else if (!betalingStatus) {
			hulp = true;
		}

		try {
			transaction.begin();

			Query query = em.createNamedQuery("Bestelling.veranderBetalingStatus");

			query.setParameter("idOrder", id);
			query.setParameter("betalingStatus", hulp);

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
