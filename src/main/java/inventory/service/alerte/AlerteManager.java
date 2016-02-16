/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.alerte;

import java.util.List;

import inventory.model.Alerte;

/**
 *
 * @author Ghiz LOTFI
 */
public interface AlerteManager {
    public List<Alerte> listAlertes() throws Exception;
    public void addAlerte(Alerte d) throws Exception;
    public void updateAlerte(Alerte depot) throws Exception;
    public void deleteAlerte(int id) throws Exception;
    public List<Alerte> findAlertes(String key) throws Exception;
    public Alerte getAlerteById(int id) throws Exception;
}
