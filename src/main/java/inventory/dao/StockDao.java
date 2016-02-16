/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import java.util.List;

import inventory.model.Stock;

/**
 *
 * @author Ghiz LOTFI
 */
public interface StockDao {
    public List<Stock> list() throws Exception;
    public void add(Stock s) throws Exception;
    public Stock getStockById(int id) throws Exception;
    public void update(Stock stock) throws Exception;
    public void delete(int id) throws Exception;
    public List<Stock> find(String key) throws Exception;
    public List<Stock> getStocksByDepot(int id) throws Exception;
}
