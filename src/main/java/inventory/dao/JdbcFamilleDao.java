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

import inventory.model.Famille;

/**
 *
 * @author Ghiz LOTFI
 */
@Repository
@Transactional
public class JdbcFamilleDao implements FamilleDao {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;

    @Override
    public List<Famille> list() throws Exception {
        logger.info("JdbcFamilleDao: Getting list of all families");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM Famille");
        @SuppressWarnings("unchecked")
		List<Famille> products = query.list();
        
        if (products.size() > 0) {
            return products;
        } else {
            throw new Exception("JdbcFamilleDao: No families found in the database");
        }
    }

    @Override
    public void add(Famille f) throws Exception {
        logger.info("JDBCFamilleDao: Adding a family ... ");
        Session session=sessionFactory.getCurrentSession();
        try {
            session.save(f);
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public void update(Famille famille) throws Exception {
        logger.info("JdbcFamilleDao: Updating a family");
        Session session=sessionFactory.getCurrentSession();
        try {
            session.update(famille);
        } catch (Exception e) {
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcFamilleDao: Deleting a family...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("DELETE Famille WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public List<Famille> find(String key) throws Exception {
        logger.info("JdbcFamilleDao: finding families...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("FROM Famille WHERE nomFamille LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Famille> products = query.list();
            return products;
        } catch (Exception e) {
            throw e;
        }
    }

	@Override
	public Famille getFamilleById(int id) throws Exception {
		logger.info("JdbcFamilleDao: find Famille by id...");
        Session session=sessionFactory.getCurrentSession();
        Famille c=(Famille) session.get(Famille.class,id);
		return c;
	}

	@Override
	public Famille getFamilleByName(String name) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Famille WHERE nomFamille LIKE :key")
                .setParameter("key", "%" + name + "%");
		Famille d = (Famille) query.uniqueResult();
		return d;
	}
}

