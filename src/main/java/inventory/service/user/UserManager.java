package inventory.service.user;

import inventory.model.User;
import java.io.Serializable;
import java.util.List;

public interface UserManager extends Serializable {

    public List<User> listUsers() throws Exception;
//    public  User authenticate(User user) throws Exception;
    public void addUser(User user) throws Exception;
    public void updateUser(User user) throws Exception;
    public void deleteUser(User user);
    public List<User> findUsers(String keyword);
//    public User getUserById(int id) throws Exception;
    public User findByUserName(String username);
    public User findByUserId(int id);
}
