/*
 * cette classe a pour objectif de récuprer les données de l'utilisateur
 * pour s'authentifier au lieu de les saisir dans le fichier spring-config.xml
 */
package inventory.service.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inventory.dao.UserDao;
import inventory.model.User;
import inventory.model.UserRole;
import inventory.model.UserStatus;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	//get user from the database, via Hibernate
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
	
		User user = userDao.findByUserName(username);
		
		if(user!=null){
			String password = user.getPassword();
			//additional information on the security object
			boolean enabled = user.getEnabled().equals(UserStatus.ACTIVE);
			boolean accountNonExpired = user.getEnabled().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getEnabled().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getEnabled().equals(UserStatus.ACTIVE);
			
			//let's populate user roles
			Collection<GrantedAuthority> authorities = 
	                                      new ArrayList<GrantedAuthority>();
			for(UserRole role : user.getUserRole()){
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
			//Now let's create Spring security User Object
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User(username, password,
					enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return securityUser;
		}
		else {
			throw new UsernameNotFoundException("User not found!");
		}

		//return (UserDetails) buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
//	private User buildUserForAuthentication(User user, 
//		List<GrantedAuthority> authorities) {
//		return new User(user.getUsername(), user.getPassword(),user.isEnabled());
//		//, true, true, true, authorities);
//	}

//	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
//
//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//		// Build user's authorities
//		for (UserRole userRole : userRoles) {
//			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//		}
//
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//
//		return Result;
//	}
//
}
