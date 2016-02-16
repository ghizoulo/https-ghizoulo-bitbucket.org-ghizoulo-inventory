package inventory.service.userRole;

import java.util.List;

import inventory.model.UserRole;

public interface UserRoleManager {
	
	public List<UserRole> listUsers() throws Exception;
	public void addUserRole(UserRole user) throws Exception;
	public void updateUser(UserRole user) throws Exception;
	public void deleteUserRole(UserRole user);
	public List<UserRole> findUsers(String keyword);
	public UserRole findByUserName(String username);
}
