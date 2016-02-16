/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import inventory.model.Depot;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ghiz LOTFI
 */
@Repository
@Transactional
public class JdbcDepotDao implements DepotDao {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;

    @Override
    public List<Depot> list() throws Exception {
        logger.info("JdbcDepotDao: Getting list of all depots");
        Session session=sessionFactory.getCurrentSession();
        
        
        query = session.createQuery("FROM Depot");
        @SuppressWarnings("unchecked")
		List<Depot> depots = query.list();
        
        
        if (depots.size() > 0) {
            return depots;
        } else {
            throw new Exception("JdbcDepotDao: No depots found in the database");
        }
    }

    @Override
    public void add(Depot p) throws Exception {
        logger.info("JDBCDepotDao: Adding a depot ... ");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.save(p);
            
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public void update(Depot depot) throws Exception {
        logger.info("JdbcDepotDao: Updating a depot");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.update(depot);
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcDepotDao: Deleting depot...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("DELETE Depot WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
            
        } catch (Exception e) {
            
            session.clear();
        }
    }

    @Override
    public List<Depot> find(String key) throws Exception {
        logger.info("JdbcDepotDao: finding depots...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("FROM Depot WHERE nomDepot LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Depot> depots = query.list();
            
            return depots;
        } catch (Exception e) {
            throw e;
        }

    }

	@Override
	public Depot getDepotById(int id) throws Exception {
		logger.info("JdbcDepotDao: find Depot by id...");
        Session session=sessionFactory.getCurrentSession();
		Depot c=(Depot) session.get(Depot.class,id);
		return c;
	}

	@Override
	public Depot getDepotByName(String name) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Depot WHERE nomDepot LIKE :key")
                .setParameter("key", "%" + name + "%");
        Depot d = (Depot) query.uniqueResult();
		return d;
	}

}

