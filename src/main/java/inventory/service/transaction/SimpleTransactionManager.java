package inventory.service.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.TransactionDao;
import inventory.model.Transaction;

@Service("transactionServiceImpl")
public class SimpleTransactionManager implements TransactionManager {
	private static final long serialVersionUID = 1L;
	@Autowired
    private TransactionDao transactionDao;
  
    @Override
    public List<Transaction> listTransactions() throws Exception{
        return transactionDao.list() ;
    }

    @Override
    public void addTransaction(Transaction p) throws Exception{
        transactionDao.add(p);
    }

    @Override
    public void updateTransaction(Transaction transaction) throws Exception {
        transactionDao.update(transaction);
    }

    @Override
    public void deleteTransaction(int id) throws Exception{
        transactionDao.delete(id);
    }
    
    @Override
    public List<Transaction> findTransactions(String key) throws Exception{
        return transactionDao.find(key);
    }

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Transaction getTransactionById(int id) throws Exception {
		return transactionDao.getTransactionById(id);
	}
}
