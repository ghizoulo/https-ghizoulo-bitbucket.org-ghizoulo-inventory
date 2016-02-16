/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.depot;

import inventory.model.Depot;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 *
 * @author Ghiz LOTFI
 */
public interface DepotManager {
    public List<Depot> listDepots() throws Exception;
    public void addDepot(Depot d) throws Exception;
    public void updateDepot(Depot depot) throws Exception;
    public void deleteDepot(int id) throws Exception;
    public List<Depot> findDepots(String key) throws Exception;
	public ArrayList<SelectItem> getAllNames()throws Exception;
	public Depot getDepotById(int id) throws Exception;
	public Depot getDepotByName(String nomDepot) throws Exception;
}
