/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.depot;

import inventory.model.Depot;
import inventory.dao.DepotDao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.faces.model.SelectItem;
/**
 *
 * @author Ghiz LOTFI
 */
@Service("depotServiceImpl")
public class SimpleDepotManager implements DepotManager {

    @Autowired
    private DepotDao depotDao;
  
    @Override
    public List<Depot> listDepots() throws Exception{
        return depotDao.list() ;
    }
    @Override
	public ArrayList<SelectItem> getAllNames() throws Exception{
    	ArrayList<SelectItem> listDepots=new ArrayList<SelectItem>();
    	for(Depot vh:(depotDao.list())){ 
    		listDepots.add(new SelectItem(vh.getNomDepot()));
	     	}
		return listDepots;
	}
    @Override
    public void addDepot(Depot d) throws Exception{
        depotDao.add(d);
    }

    @Override
    public void updateDepot(Depot depot) throws Exception {
        depotDao.update(depot);
    }

    @Override
    public void deleteDepot(int id) throws Exception{
        depotDao.delete(id);
    }
    
    @Override
    public List<Depot> findDepots(String key) throws Exception{
        return depotDao.find(key);
    }

    public void setDepotDao(DepotDao depotDao) {
        this.depotDao = depotDao;
    }

	public DepotDao getDepotDao() {
		return depotDao;
	}
	@Override
	public Depot getDepotById(int id) throws Exception {
		return depotDao.getDepotById(id);
	}
	@Override
	public Depot getDepotByName(String nomDepot) throws Exception {
		return depotDao.getDepotByName(nomDepot);
	}
    
}
