/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import inventory.model.Depot;
import java.util.List;

/**
 *
 * @author Ghiz LOTFI
 */
public interface DepotDao {
    public List<Depot> list() throws Exception;
    public void add(Depot p) throws Exception;
    public Depot getDepotById(int id) throws Exception;
    public Depot getDepotByName(String name) throws Exception;
    public void update(Depot depot) throws Exception;
    public void delete(int id) throws Exception;
    public List<Depot> find(String key) throws Exception;
}
