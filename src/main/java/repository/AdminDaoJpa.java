package repository;

import domein.Admin;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

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
}
