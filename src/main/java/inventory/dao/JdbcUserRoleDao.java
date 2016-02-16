package inventory.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inventory.model.UserRole;

@Repository
@Transactional
public class JdbcUserRoleDao implements UserRoleDao{

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;
    
    @Override
    public List<UserRole> list() throws Exception{
        logger.info("JdbcUserDao: Getting list of all users");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM UserRole");
        @SuppressWarnings("unchecked")
		List<UserRole> users = query.list();
        
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database");
    }

    @Override
    public void add(UserRole user) throws Exception {
        logger.info("JdbcUserDao: Adding user...");
        Session session=sessionFactory.getCurrentSession();
        
        try{
            session.save(user);
        }catch(Exception e){
            session.clear();
            throw new Exception(e.getStackTrace()+"JdbcUserDao: The userRole already exists in the database");
        }
    }

    @Override
    public void update(UserRole user) throws Exception{
        logger.info("JdbcUserDao: Updating user...");
        Session session=sessionFactory.getCurrentSession();
        
        try{
            session.update(user);
        }catch(Exception e){
            throw new Exception("JdbcUserDao: The user already exists in the database");
        }
    }
    
    @Override
    public void delete(UserRole user){
        logger.info("JdbcUserDao: Deleting user...");
        Session session=sessionFactory.getCurrentSession();
        
        	query = session.createQuery("DELETE UserRole WHERE username=:id and role=:key")
                    .setParameter("id", user.getUser().getUsername()).setParameter("key", user.getRole());
            query.executeUpdate();
        
    }

    @Override
    public List<UserRole> find(String keyword) throws Exception{
        logger.info("JdbcUserDao: Getting list of all users corresponding to " + keyword);
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM UserRole WHERE username LIKE :keyword")
                .setParameter("keyword", "%"+keyword+"%");
        @SuppressWarnings("unchecked")
		List<UserRole> users = query.list();
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database corresponding to " + keyword);
    }

	@Override
	@SuppressWarnings("unchecked")
	public UserRole findByUserName(String username) {
		logger.info("JdbcUserDao: Finding User by username ");
		List<UserRole> users = new ArrayList<UserRole>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from UserRole where username=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
}
