/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dao;

import java.util.List;

import inventory.model.Famille;

/**
 *
 * @author Ghiz LOTFI
 */
public interface FamilleDao {
    public List<Famille> list() throws Exception;
    public void add(Famille f) throws Exception;
    public Famille getFamilleById(int id) throws Exception;
    public void update(Famille famille) throws Exception;
    public void delete(int id) throws Exception;
    public List<Famille> find(String key) throws Exception;
    public Famille getFamilleByName(String name) throws Exception;
}
