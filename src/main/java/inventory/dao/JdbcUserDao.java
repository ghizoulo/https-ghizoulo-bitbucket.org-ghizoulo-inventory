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

import inventory.model.User;

@Repository
@Transactional
public class JdbcUserDao implements UserDao{

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    private Query query;
    
    @Override
    public List<User> list() throws Exception{
        logger.info("JdbcUserDao: Getting list of all users");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM User");
        @SuppressWarnings("unchecked")
		List<User> users = query.list();
        
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database");
    }
    
//    @Override
//    public User authenticate(User user) throws Exception{
//        logger.info("JdbcUserDao: Authenticating...");
//        Session session=sessionFactory.getCurrentSession();
//        
//        query = session.createQuery("FROM User WHERE username=:username AND password=:password")
//                .setParameter("username", user.getUsername())
//                .setParameter("password", user.getPassword());
//        user=null;
//        user = (User) query.uniqueResult();
//        
//        if(user != null)
//            return user;
//        else
//            throw new Exception("JdbcUserDao: Authenticating failed. No such user with username="+user.getUsername()+" and password="+user.getPassword());
//    }

    @Override
    public void add(User user) throws Exception {
        logger.info("JdbcUserDao: Adding user...");
        Session session=sessionFactory.getCurrentSession();
        
        try{
            session.save(user);
        }catch(Exception e){
            session.clear();
            throw new Exception("JdbcUserDao: The User already exists in the database");
        }
    }

    @Override
    public void update(User user) throws Exception{
        logger.info("JdbcUserDao: Updating user...");
        Session session=sessionFactory.getCurrentSession();
        
        try{
            session.update(user);
        }catch(Exception e){
            throw new Exception("JdbcUserDao: The login already exists in the database");
        }
    }
    
    @Override
    public void delete(User user){
        logger.info("JdbcUserDao: Deleting user...");
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("DELETE User WHERE username=:id")
                .setParameter("id", user.getUsername());
        query.executeUpdate();
    }

    @Override
    public List<User> find(String keyword) throws Exception{
        logger.info("JdbcUserDao: Getting list of all users corresponding to " + keyword);
        Session session=sessionFactory.getCurrentSession();
        
        query = session.createQuery("FROM User WHERE username LIKE :keyword")
                .setParameter("keyword", "%"+keyword+"%");
        @SuppressWarnings("unchecked")
		List<User> users = query.list();
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database corresponding to " + keyword);
    }

//	@Override
//	public User getUserById(int id) throws Exception {
//		logger.info("JdbcUserDao: find User by id...");
//        Session session=sessionFactory.getCurrentSession();
//		User c=(User) session.get(User.class,id);
//		return c;
//	}

	@Override
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		logger.info("JdbcUserDao: Finding User by username ");
		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Override
	public User findByUserId(int id) {
		logger.info("JdbcUserDao: find User by id...");
        Session session=sessionFactory.getCurrentSession();
        User c=(User) session.get(User.class,id);
		return c;
	}
}
