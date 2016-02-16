/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import inventory.model.Inventaire;
import java.util.List;

/**
 *
 * @author Ghiz LOTFI
 */
public interface InventaireDao {
    public List<Inventaire> list() throws Exception;
    public void add(Inventaire i) throws Exception;
    public Inventaire getInventaireById(int id) throws Exception;
    public void update(Inventaire inventaire) throws Exception;
    public void delete(int id) throws Exception;
    public List<Inventaire> find(String key) throws Exception;
}
