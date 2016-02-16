/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.service.famille;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import inventory.model.Famille;

/**
 *
 * @author Ghiz LOTFI
 */
public interface FamilleManager {
    public List<Famille> listFamilles() throws Exception;
    public void addFamille(Famille f) throws Exception;
    public void updateFamille(Famille famille) throws Exception;
    public void deleteFamille(int id) throws Exception;
    public List<Famille> findFamilles(String key) throws Exception;
    public Famille getFamilleById(int id)throws Exception;
    public Famille getFamilleByName(String Famille) throws Exception;
    public ArrayList<SelectItem> getAllNames()throws Exception;
}
