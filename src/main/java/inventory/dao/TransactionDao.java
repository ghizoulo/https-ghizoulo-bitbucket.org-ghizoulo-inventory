package inventory.dao;

import java.util.List;

import inventory.model.Transaction;

public interface TransactionDao {
	public List<Transaction> list() throws Exception;
    public void add(Transaction p) throws Exception;
    public Transaction getTransactionById(int id) throws Exception;
    public void update(Transaction transaction) throws Exception;
    public void delete(int id) throws Exception;
    public List<Transaction> find(String key) throws Exception;
//    public void addTrans(int idT, int idP) throws Exception;
}
