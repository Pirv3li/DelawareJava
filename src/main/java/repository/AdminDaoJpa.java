package repository;

import java.util.List;

import domein.Admin;
import domein.Bedrijf;
import domein.GoedKeuringLeverancier;
import domein.Interface_GoedKeuringLeverancier;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDaoJpa extends GenericDaoJpa<Admin> implements AdminDao {
	
	public AdminDaoJpa() {
		super(Admin.class);
	}
	
	
	@Override
	public Admin getAdminByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Admin.getAdminByGebruikersnaam", Admin.class)
					.setParameter("gebruikersnaam", gebruikersnaam).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
	
	//bedrijfJpa
	
    public List<Bedrijf> getBedrijven() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bedrijf.getBedrijven", Bedrijf.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    //goedKeuringJpa
    
	public void keuringVeranderVerzoekenLeverancier(String id, String afgehandeld) throws EntityNotFoundException {
		System.out.println("id: " + id + " afgehandeld: " + afgehandeld);
		EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();

				Query query = em.createNamedQuery("GoedKeuringLeverancier.keuringVeranderVerzoekenLeverancier");

				query.setParameter("idGoedkeuringLeverancier", Integer.parseInt(id));
				
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
