package repository;

import domein.Product;
import jakarta.persistence.EntityNotFoundException;

public interface ProductDao extends GenericDao<Product> {
    public Product getProductByProductId(int id) throws EntityNotFoundException;   
}
