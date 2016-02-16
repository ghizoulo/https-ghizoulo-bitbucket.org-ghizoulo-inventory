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

import inventory.model.Inventaire;

/**
 *
 * @author Ghiz LOTFI
 * cette classe n'est pas encore utilisée
 */
@Repository
@Transactional
public class JdbcInventaireDao implements InventaireDao {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;

    @Override
    public List<Inventaire> list() throws Exception {
        logger.info("JdbcInventaireDao: Getting list of all inventories");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM Inventaire");
        @SuppressWarnings("unchecked")
		List<Inventaire> inventaires = query.list();
        
        if (inventaires.size() > 0) {
            return inventaires;
        } else {
            throw new Exception("JdbcInventaireDao: No inventories found in the database");
        }
    }

    @Override
    public void add(Inventaire i) throws Exception {
        logger.info("JDBCInventaireDao: Adding an inventory ... ");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.save(i);
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public void update(Inventaire inventaire) throws Exception {
        logger.info("JdbcInventaireDao: Updating an inventory");
        Session session=sessionFactory.getCurrentSession();
        try {
            session.update(inventaire);
        } catch (Exception e) {
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcInventaireDao: Deleting inventory...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("DELETE Inventaire WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public List<Inventaire> find(String key) throws Exception {
        logger.info("JdbcInventaireDao: finding inventories...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("FROM Inventaire WHERE dateInventaire LIKE :key OR responsableI LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Inventaire> inventaires = query.list();
            return inventaires;
        } catch (Exception e) {
            throw e;
        }

    }

	@Override
	public Inventaire getInventaireById(int id) throws Exception {
		logger.info("JdbcInventaireDao: find Inventaire by id...");
        Session session=sessionFactory.getCurrentSession();
		Inventaire c=(Inventaire) session.get(Inventaire.class,id);
		return c;
	}

}
