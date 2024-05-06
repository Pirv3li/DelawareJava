package repository;
import java.util.List;

import domein.Bedrijf;
import domein.Interface_Bedrijf;
import jakarta.persistence.EntityNotFoundException;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public Interface_Bedrijf getBedrijfById(int id) throws EntityNotFoundException;   
        public Interface_Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException;
        public List<Bedrijf> getBedrijven() throws EntityNotFoundException;
}