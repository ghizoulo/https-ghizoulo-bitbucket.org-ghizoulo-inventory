package inventory.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inventory.model.Product;

@Repository
@Transactional
public class JdbcProductDao implements ProductDao {

    protected final Log logger = LogFactory.getLog(getClass());
    private Query query;
    
    @Autowired
    private SessionFactory sessionFactory;
    

    @Override
    public List<Product> list() throws Exception {
        logger.info("JdbcProductDao: Getting list of all products");
        Session session=sessionFactory.getCurrentSession();
        
        
        query = session.createQuery("FROM Product");
        @SuppressWarnings("unchecked")
		List<Product> products = query.list();
        
        
        if (products.size() > 0) {
            return products;
        } else {
            throw new Exception("JdbcProductDao: No products found in the database");
        }
    }

    @Override
    public void add(Product p) throws Exception {
        logger.info("JDBCProductDao: Adding a product ... ");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.save(p);
            
        } catch (Exception e) {
             //Annuler la transaction
            session.clear();
        }
    }

    @Override
    public void update(Product product) throws Exception {
        logger.info("JdbcProductDao: Updating a product");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.update(product);
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcProductDao: Deleting product...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("DELETE Product WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
            
        } catch (Exception e) {
            
            session.clear();
        }
    }

    @Override
    public List<Product> find(String key) throws Exception {
        logger.info("JdbcProductDao: finding products...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("FROM Product WHERE libelle LIKE :key OR designation LIKE :key OR fournisseur LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Product> products = query.list();
            
            return products;
        } catch (Exception e) {
            throw e;
        }
    }

	@Override
	public Product getProductById(int id) throws Exception {
		logger.info("JdbcProduitDao: find Produit by id...");
        Session session=sessionFactory.getCurrentSession();
		Product c=(Product) session.get(Product.class,id);
		return c;
	}

}
