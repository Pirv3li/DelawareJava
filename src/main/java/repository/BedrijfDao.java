package repository;
import java.util.List;

import domein.Bedrijf;
import jakarta.persistence.EntityNotFoundException;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public Bedrijf getBedrijfById(int id) throws EntityNotFoundException;   
        public Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException;
        public List<Bedrijf> getBedrijven() throws EntityNotFoundException;
}