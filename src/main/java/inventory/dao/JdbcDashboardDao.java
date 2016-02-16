/*
 * cette classe permet de récupérer le nombre de produit et d'alerte dans notre systeme
 * pour les afficher dans le tableau de bord
 */
package inventory.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JdbcDashboardDao implements DashboardDao{

	protected final Log logger = LogFactory.getLog(getClass());
    private Query query;
    
    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public Long getProductsNbr() throws Exception {
		 logger.info("JdbcDashboardDao: number of products...");
	        Session session=sessionFactory.getCurrentSession();
	        try {
	            
	            query = session.createQuery("SELECT COUNT(idTag) FROM Product");
	            Long products = (Long) query.uniqueResult();
	            
	            return products;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Override
	public Long getAlertsNbr() throws Exception {
		logger.info("JdbcDashboardDao: number of alertes...");
        Session session=sessionFactory.getCurrentSession();
        try {
        	query = session.createQuery("SELECT COUNT(*) FROM Alerte WHERE dateArret is null");
            Long products = (Long) query.uniqueResult();
            
            return products;
        } catch (Exception e) {
            throw e;
        }
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long getStocksNbr() throws Exception {
		logger.info("JdbcDashboardDao: number of Stocks...");
        Session session=sessionFactory.getCurrentSession();
        try {
        	query = session.createQuery("SELECT COUNT(*) FROM Stock");
            Long products = (Long) query.uniqueResult();
            
            return products;
        } catch (Exception e) {
            throw e;
        }
	}

	@Override
	public Long getFamillesNbr() throws Exception {
		logger.info("JdbcDashboardDao: number of Familles...");
        Session session=sessionFactory.getCurrentSession();
        try {
        	query = session.createQuery("SELECT COUNT(*) FROM Famille");
            Long products = (Long) query.uniqueResult();
            
            return products;
        } catch (Exception e) {
            throw e;
        }
	}

}
