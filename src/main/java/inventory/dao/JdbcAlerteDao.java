/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import inventory.model.Alerte;

/**
 *
 * @author Ghiz LOTFI
 */
@Repository
@Transactional
public class JdbcAlerteDao implements AlerteDao {

    protected final Log logger = LogFactory.getLog(getClass());
    private Query query;
    
    @Autowired
    private SessionFactory sessionFactory;
    

    @Override
    public List<Alerte> list() throws Exception {
        logger.info("JdbcAlerteDao: Getting list of all alerts");
        Session session=sessionFactory.getCurrentSession();
        
        
        query = session.createQuery("FROM Alerte");
        @SuppressWarnings("unchecked")
		List<Alerte> products = query.list();
        
        
        if (products.size() > 0) {
            return products;
        } else {
            throw new Exception("JdbcAlerteDao: No alerts found in the database");
        }
    }

    @Override
    public void add(Alerte a) throws Exception {
        logger.info("JDBCAlerteDao: Adding an alert ... ");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.save(a);
            
        } catch (Exception e) {
             //Annuler la transaction
            session.clear();
        }
    }

    @Override
    public void update(Alerte alerte) throws Exception {
        logger.info("JdbcAlerteDao: Updating an alert");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.update(alerte);
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcAlerteDao: Deleting alert...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("DELETE Alerte WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
            
        } catch (Exception e) {
            
            session.clear();
        }
    }
//à refaire
    @Override
    public List<Alerte> find(String key) throws Exception {
        logger.info("JdbcAlerteDao: finding alerts...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("FROM Alerte WHERE typeAlerte LIKE :key OR dateAlerte LIKE :key OR dateArret LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Alerte> products = query.list();
            
            return products;
        } catch (Exception e) {
            throw e;
        }

    }

	@Override
	public Alerte getAlerteById(int id) throws Exception {
		logger.info("JdbcAlerteDao: find Alerte by id...");
        Session session=sessionFactory.getCurrentSession();
        Alerte c=(Alerte) session.get(Alerte.class,id);
		return c;
	}

}
