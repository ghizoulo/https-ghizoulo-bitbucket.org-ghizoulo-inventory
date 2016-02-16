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

import inventory.model.Transaction;

@Repository
@Transactional
public class JdbcTransactionDao implements TransactionDao {
	protected final Log logger = LogFactory.getLog(getClass());
    private Query query;
    
    @Autowired
    private SessionFactory sessionFactory;
    

    @Override
    public List<Transaction> list() throws Exception {
        logger.info("JdbcTransactionDao: Getting list of all Transactions");
        Session session=sessionFactory.getCurrentSession();
        
        
        query = session.createQuery("FROM Transaction");
        @SuppressWarnings("unchecked")
		List<Transaction> transactions = query.list();
        
        
        if (transactions.size() > 0) {
            return transactions;
        } else {
            throw new Exception("JdbcTransactionDao: No Transactions found in the database");
        }
    }

    @Override
    public void add(Transaction p) throws Exception {
        logger.info("JDBCTransactionDao: Adding a Transaction ... ");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.save(p);
            
        } catch (Exception e) {
             //Annuler la transaction
            session.clear();
        }
    }

    @Override
    public void update(Transaction transaction) throws Exception {
        logger.info("JdbcTransactionDao: Updating a Transaction");
        Session session=sessionFactory.getCurrentSession();
        
        try {
            session.update(transaction);
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcTransactionDao: Deleting Transaction...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("DELETE Transaction WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
            
        } catch (Exception e) {
            
            session.clear();
        }
    }

    @Override
    public List<Transaction> find(String key) throws Exception {
        logger.info("JdbcTransactionDao: finding Transactions...");
        Session session=sessionFactory.getCurrentSession();
        try {
            
            query = session.createQuery("FROM Transaction WHERE libelle LIKE :key OR designation LIKE :key OR fournisseur LIKE :key")
                    .setParameter("key", "%" + key + "%");
            @SuppressWarnings("unchecked")
			List<Transaction> transactions = query.list();
            
            return transactions;
        } catch (Exception e) {
            throw e;
        }

    }

	@Override
	public Transaction getTransactionById(int id) throws Exception {
		logger.info("JdbcProduitDao: find Produit by id...");
        Session session=sessionFactory.getCurrentSession();
		Transaction c=(Transaction) session.get(Transaction.class,id);
		return c;
	}

}
