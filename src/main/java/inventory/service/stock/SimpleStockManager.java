/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.stock;

import inventory.model.Stock;
import inventory.dao.StockDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ghiz LOTFI
 */
@Service("stockServiceImpl")
public class SimpleStockManager implements StockManager {

    @Autowired
    private StockDao stockDao;
  
    @Override
    public List<Stock> listStocks() throws Exception{
        return stockDao.list() ;
    }

    @Override
    public void addStock(Stock s) throws Exception{
        stockDao.add(s);
    }

    @Override
    public void updateStock(Stock stock) throws Exception {
        stockDao.update(stock);
    }

    @Override
    public void deleteStock(int id) throws Exception{
        stockDao.delete(id);
    }
    
    @Override
    public List<Stock> findStocks(String key) throws Exception{
        return stockDao.find(key);
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

	public StockDao getStockDao() {
		return stockDao;
	}

	@Override
	public Stock getStockById(int id) throws Exception {
		return stockDao.getStockById(id);
	}

	@Override
	public List<Stock> getStocksByDepot(int id) throws Exception {
		return stockDao.getStocksByDepot(id);
	}
    
}

