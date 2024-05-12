package repository;

import java.util.Date;
import java.util.List;

import domein.Admin;
import domein.Adres;
import domein.Bedrijf;
import domein.GoedKeuringLeverancier;
import domein.Interface_GoedKeuringLeverancier;
import domein.Leverancier;
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
	
    public List<Bedrijf> getBedrijven(int aantal, int begin) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Bedrijf.getBedrijven", Bedrijf.class)
                    .setFirstResult(begin)
                    .setMaxResults(aantal)
                    .getResultList();
            	
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    //goedKeuringJpa
    
    public void keuringVeranderVerzoekenLeverancier(int id, String afgehandeld) throws EntityNotFoundException {
        System.out.println("id: " + id + " afgehandeld: " + afgehandeld);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            GoedKeuringLeverancier keuringLeverancier = em.find(GoedKeuringLeverancier.class, id);
            if (keuringLeverancier != null) {
                keuringLeverancier.setAfgehandeld(afgehandeld);
            } else {
                throw new EntityNotFoundException("GoedKeuringLeverancier not found with id: " + id);
            }

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw ex;
        }
    }

	
	public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld, int aantal, int begin)
			throws EntityNotFoundException {
		try {
			List<GoedKeuringLeverancier> resultList = em
					.createNamedQuery("GoedKeuringLeverancier.getAllByStatusAfhandeling", GoedKeuringLeverancier.class)
					.setParameter("afgehandeld", afgehandeld)
					.setFirstResult(begin)
					.setMaxResults(aantal)
					.getResultList();
			return FXCollections.observableList(resultList);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
	
	
	public void updateBedrijfByIdBedrijf(int idBedrijf, String iban, String btwNummer, String telefoonnummer, String sector, int idAdres) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();

	        Bedrijf bedrijf = em.find(Bedrijf.class, idBedrijf);
	        if (bedrijf == null) {
	            throw new EntityNotFoundException("Bedrijf not found with ID: " + idBedrijf);
	        }

	        bedrijf.setIban(iban);
	        bedrijf.setBtwNummer(btwNummer);
	        bedrijf.setTelefoonnummer(telefoonnummer);
	        bedrijf.setSector(sector);
	        bedrijf.setIdAdres(idAdres);

	        transaction.commit();
	    } catch (Exception ex) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	}
	
	// AdresJPA
	
	public int createAdres(String straat, String nummer, String stad, String postcode) throws EntityNotFoundException {
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();

	        Date date = new Date();
	        Adres newAdres = new Adres(straat, nummer, stad, postcode, date);
	        em.persist(newAdres);

	        em.flush();

	        transaction.commit();

	        return newAdres.getIdAdres();
	    } catch (Exception ex) {
	        // If an exception occurs, rollback the transaction
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	}
    
    //leverancierJPA
    
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email) {
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        transaction.begin();

	        Leverancier leverancier = em.find(Leverancier.class, idLeverancier);
	        if (leverancier == null) {
	            throw new EntityNotFoundException("Leverancier with ID " + idLeverancier + " not found");
	        }

	        leverancier.setGebruikersnaam(gebruikersnaam);
	        leverancier.setEmail(email);

	        em.merge(leverancier); // Update the leverancier entity

	        transaction.commit();
	    } catch (Exception ex) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw ex;
	    }
	}



	
}
