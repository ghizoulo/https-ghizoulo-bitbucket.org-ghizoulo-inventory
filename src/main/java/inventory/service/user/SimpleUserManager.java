package inventory.service.user;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.UserDao;
import inventory.model.User;

@Service("userServiceImpl")
public class SimpleUserManager implements UserManager {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private UserDao userDao;

    @Override
    public List<User> listUsers() throws Exception {
        return userDao.list();
    }


    @Override
    public void addUser(User user) throws Exception {
        userDao.add(user);
    }
    
    @Override
    public void updateUser(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }
    
    @Override
    public List<User> findUsers(String keyword) {
        List<User> users = new LinkedList<User>();
        try {
            users= userDao.find(keyword);
        } catch (Exception e) {
        }
        return users;
    }

    public void setUserDao(UserDao userDao) throws Exception {
        this.userDao = userDao;
    }

	@Override
	public User findByUserName(String username) {
		return this.userDao.findByUserName(username);
	}


	@Override
	public User findByUserId(int id) {
		return userDao.findByUserId(id);
	}

}
