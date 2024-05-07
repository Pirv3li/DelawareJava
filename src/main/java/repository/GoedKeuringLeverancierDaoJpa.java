package repository;

import java.util.List;

import domein.Bestelling;
import domein.GoedKeuringLeverancier;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoedKeuringLeverancierDaoJpa extends GenericDaoJpa<GoedKeuringLeverancier>
		implements GoedKeuringLeverancierDao {

	public GoedKeuringLeverancierDaoJpa() {
		super(GoedKeuringLeverancier.class);
	}

	@Override
	public void keuringVeranderVerzoekenLeverancier(String id, String afgehandeld) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		boolean doen = false;
		if (afgehandeld == "goedgekeurd") {
			doen = true;

			try {
				transaction.begin();

				Query query = em.createNamedQuery("GoedKeuringLeverancier.veranderBetalingStatus");

				query.setParameter("idGoedkeuringLeverancier", id);
				query.setParameter("afgehandeld", afgehandeld);

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

	@Override
	public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld)
			throws EntityNotFoundException {
		try {
			List<GoedKeuringLeverancier> resultList = em
					.createNamedQuery("GoedKeuringLeverancier.getAllByStatusAfhandeling", GoedKeuringLeverancier.class)
					.setParameter("afgehandeld", afgehandeld).getResultList();
			return FXCollections.observableList(resultList);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

}
