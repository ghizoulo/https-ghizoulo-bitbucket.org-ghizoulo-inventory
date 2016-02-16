/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.alerte;

import inventory.model.Alerte;
import inventory.dao.AlerteDao;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ghiz LOTFI
 */
@Service("alerteServiceImpl")
public class SimpleAlerteManager implements AlerteManager,Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private AlerteDao alerteDao;
  
    @Override
    public List<Alerte> listAlertes() throws Exception{
        return alerteDao.list() ;
    }

    @Override
    public void addAlerte(Alerte d) throws Exception{
    	alerteDao.add(d);
    }

    @Override
    public void updateAlerte(Alerte alerte) throws Exception {
    	alerteDao.update(alerte);
    }

    @Override
    public void deleteAlerte(int id) throws Exception{
    	alerteDao.delete(id);
    }
    
    @Override
    public List<Alerte> findAlertes(String key) throws Exception{
        return alerteDao.find(key);
    }

    public void setAlerteDao(AlerteDao alerteDao) {
        this.alerteDao = alerteDao;
    }

	public AlerteDao getAlerteDao() {
		return alerteDao;
	}

	@Override
	public Alerte getAlerteById(int id) throws Exception {
		return alerteDao.getAlerteById(id);
	}
    
}

