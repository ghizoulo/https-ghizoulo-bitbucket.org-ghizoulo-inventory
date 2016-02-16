package inventory.service.userRole;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.UserRoleDao;
import inventory.model.UserRole;

@Service("userRoleServiceImpl")
public class SimpleUserRoleManager implements UserRoleManager {

	@Autowired
    private UserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> listUsers() throws Exception {
		return userRoleDao.list();
	}

	@Override
	public void addUserRole(UserRole user) throws Exception {
		userRoleDao.add(user);
	}

	@Override
	public void updateUser(UserRole user) throws Exception {
		userRoleDao.update(user);
	}

	@Override
	public void deleteUserRole(UserRole user) {
		userRoleDao.delete(user);
	}

	@Override
	public List<UserRole> findUsers(String keyword) {
		List<UserRole> users = new LinkedList<UserRole>();
        try {
            users= userRoleDao.find(keyword);
        } catch (Exception e) {
        }
        return users;
	}

	@Override
	public UserRole findByUserName(String username) {
		return userRoleDao.findByUserName(username);
	}

}
