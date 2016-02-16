/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.inventaire;

import inventory.model.Inventaire;
import java.util.List;

/**
 *
 * @author Ghiz LOTFI
 */
public interface InventaireManager {
    public List<Inventaire>     listInventaires() throws Exception;
    public List<Inventaire>     findInventaires(String key) throws Exception;
    public void                 addInventaire(Inventaire i) throws Exception;
    public void                 updateInventaire(Inventaire depot) throws Exception;
    public void                 deleteInventaire(int id) throws Exception;
    public Inventaire getInventaireById(int id)throws Exception;
}
