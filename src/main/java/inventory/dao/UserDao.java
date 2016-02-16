package inventory.dao;

import java.util.List;

import inventory.model.User;

public interface UserDao {
    
    public List<User> list() throws Exception;
//    public User authenticate(User user) throws Exception;
    public void add(User user) throws Exception;
//    public User getUserById(int id) throws Exception;
    public void update(User user) throws Exception;
    public void delete(User user);
    public List<User> find(String keyword) throws Exception;
    public User findByUserName(String username);
    public User findByUserId(int id);
}
