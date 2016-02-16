package inventory.service.transaction;

import java.util.List;

import inventory.model.Transaction;

public interface TransactionManager {

    public List<Transaction> listTransactions() throws Exception;
    public void addTransaction(Transaction p) throws Exception;
    public void updateTransaction(Transaction transaction) throws Exception;
    public void deleteTransaction(int id) throws Exception;
    public List<Transaction> findTransactions(String key) throws Exception;
    public Transaction getTransactionById(int id)throws Exception;
}
