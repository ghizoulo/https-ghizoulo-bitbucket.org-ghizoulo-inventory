/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.stock;

import inventory.model.Stock;
import java.util.List;

/**
 *
 * @author Ghiz LOTFI
 */
public interface StockManager {
    public List<Stock> listStocks() throws Exception;
    public void addStock(Stock s) throws Exception;
    public void updateStock(Stock stock) throws Exception;
    public void deleteStock(int id) throws Exception;
    public List<Stock> findStocks(String key) throws Exception;
    public Stock getStockById(int id) throws Exception;
    List<Stock> getStocksByDepot(int id) throws Exception;
}
