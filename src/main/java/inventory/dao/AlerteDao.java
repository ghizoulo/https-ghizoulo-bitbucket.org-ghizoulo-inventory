/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import inventory.model.Alerte;

import java.util.List;

/**
 *
 * @author Ghiz LOTFI
 */
public interface AlerteDao {
    public List<Alerte> list() throws Exception;
    public void add(Alerte a) throws Exception;
    public Alerte getAlerteById(int id) throws Exception;
    public void update(Alerte alerte) throws Exception;
    public void delete(int id) throws Exception;
    public List<Alerte> find(String key) throws Exception;
}
