package repository;

import domein.Admin;
import jakarta.persistence.EntityNotFoundException;

public interface AdminDao extends GenericDao<Admin>{
	public Admin getAdminByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;   

}
