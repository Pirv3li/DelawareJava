package repository;
import domein.Bedrijf;
import jakarta.persistence.EntityNotFoundException;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public Bedrijf getBedrijfById(int id) throws EntityNotFoundException;   
}