package inventory.dao;

import java.util.List;

import inventory.model.UserRole;

public interface UserRoleDao {
    
    public List<UserRole> list() throws Exception;
    public void add(UserRole user) throws Exception;
    public void update(UserRole user) throws Exception;
    public void delete(UserRole user);
    public List<UserRole> find(String keyword) throws Exception;
    public UserRole findByUserName(String username);
}
