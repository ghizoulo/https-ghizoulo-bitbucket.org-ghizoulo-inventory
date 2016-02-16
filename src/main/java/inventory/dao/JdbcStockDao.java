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

import inventory.model.Stock;

/**
 *
 * @author Ghiz LOTFI
 */
@Repository
@Transactional
public class JdbcStockDao implements StockDao{

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;
    
    @Override
    public List<Stock> list() throws Exception {
        logger.info("JdbcStockDao: Getting list of all Stock");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM Stock");
        @SuppressWarnings("unchecked")
		List<Stock> stock = query.list();
        
        if (stock.size() > 0) {
            return stock;
        } else {
            throw new Exception("JdbcStockDao: No Stock found in the database");
        }
    }

    @Override
    public void add(Stock s) throws Exception {
        logger.info("JDBCStockDao: Adding a stock ... ");
        Session session=sessionFactory.getCurrentSession();
        try {
            session.save(s);
        } catch (Exception e) {
            session.clear();
        }
    }

    @Override
    public void update(Stock stock) throws Exception {
        logger.info("JdbcStockDao: Updating a stock");
        Session session=sessionFactory.getCurrentSession();
        try {
            session.update(stock);
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcStockDao: Deleting Stock...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("DELETE Stock WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            session.clear();
        }
    }
// à refaire 
    @Override
    public List<Stock> find(String key) throws Exception {
        logger.info("JdbcStockDao: finding Stocks...");
        Session session=sessionFactory.getCurrentSession();
        try {
            query = session.createQuery("FROM Stock WHERE dateReception LIKE :key OR quantite LIKE :key OR modeAlerte LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Stock> stock = query.list();
            return stock;
        } catch (Exception e) {
            throw e;
        }
    }

	@Override
	public Stock getStockById(int id) throws Exception {
		logger.info("JdbcStockDao: find Stock by id...");
        Session session=sessionFactory.getCurrentSession();
		Stock c=(Stock) session.get(Stock.class,id);
		return c;
	}

	@Override
	public List<Stock> getStocksByDepot(int id) throws Exception {
		 Session session=sessionFactory.getCurrentSession();
		 try {
	            query = session.createQuery("FROM Stock WHERE depot_id=:id")
	            		.setParameter("id", id);
	            @SuppressWarnings("unchecked")
				List<Stock> stock = query.list();
	            return stock;
	        } catch (Exception e) {
	            throw e;
	        }
	}
    
}
