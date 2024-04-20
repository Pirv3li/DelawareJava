package repository;

import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class ProductDaoJpa extends GenericDaoJpa<Product> implements ProductDao{

	
	public ProductDaoJpa() {
        super(Product.class);
    }
	
	@Override
    public Product getProductByProductId(int id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Product.getProductByProductId", Product.class)
                 .setParameter("idProduct", id)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
