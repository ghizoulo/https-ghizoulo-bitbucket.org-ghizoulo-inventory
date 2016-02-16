/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.famille;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.FamilleDao;
import inventory.model.Famille;

/**
 *
 * @author Ghiz LOTFI
 */
@Service("familleServiceImpl")
public class SimpleFamilleManager implements FamilleManager{

    @Autowired
    private FamilleDao familleDao;
  
    @Override
    public List<Famille> listFamilles() throws Exception{
        return familleDao.list() ;
    }

    @Override
    public void addFamille(Famille f) throws Exception{
        familleDao.add(f);
    }

    @Override
    public void updateFamille(Famille famille) throws Exception {
        familleDao.update(famille);
    }

    @Override
    public void deleteFamille(int id) throws Exception{
        familleDao.delete(id);
    }
    
    @Override
    public List<Famille> findFamilles(String key) throws Exception{
        return familleDao.find(key);
    }

    public void setFamilleDao(FamilleDao familleDao) {
        this.familleDao = familleDao;
    }

	public FamilleDao getFamilleDao() {
		return familleDao;
	}

	@Override
	public Famille getFamilleById(int id) throws Exception {
		return familleDao.getFamilleById(id);
	}

	@Override
	public Famille getFamilleByName(String Famille) throws Exception {
		return familleDao.getFamilleByName(Famille);
	}

	@Override
	public ArrayList<SelectItem> getAllNames() throws Exception {
		ArrayList<SelectItem> listFamilles=new ArrayList<SelectItem>();
    	for(Famille vh:(familleDao.list())){ 
    		listFamilles.add(new SelectItem(vh.getNomFamille()));
	     	}
		return listFamilles;
	}

}
