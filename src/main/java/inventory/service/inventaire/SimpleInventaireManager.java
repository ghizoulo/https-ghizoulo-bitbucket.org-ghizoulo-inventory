/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.inventaire;

import inventory.model.Inventaire;
import inventory.dao.InventaireDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ghiz LOTFI
 */
@Service("inventaireServiceImpl")
public class SimpleInventaireManager implements InventaireManager {

    @Autowired
    private InventaireDao inventaireDao;
  
    @Override
    public List<Inventaire> listInventaires() throws Exception{
        return inventaireDao.list() ;
    }

    @Override
    public void addInventaire(Inventaire i) throws Exception{
        inventaireDao.add(i);
    }

    @Override
    public void updateInventaire(Inventaire inventaire) throws Exception {
        inventaireDao.update(inventaire);
    }

    @Override
    public void deleteInventaire(int id) throws Exception{
        inventaireDao.delete(id);
    }
    
    @Override
    public List<Inventaire> findInventaires(String key) throws Exception{
        return inventaireDao.find(key);
    }

    public void setInventaireDao(InventaireDao inventaireDao) {
        this.inventaireDao = inventaireDao;
    }

	public InventaireDao getInventaireDao() {
		return inventaireDao;
	}

	@Override
	public Inventaire getInventaireById(int id) throws Exception {
		return inventaireDao.getInventaireById(id);
	}
    
}