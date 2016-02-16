/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.presentation;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import inventory.model.User;
import inventory.model.UserRole;
import inventory.model.UserStatus;
import inventory.service.user.UserManager;
import inventory.service.userRole.UserRoleManager;

/**
 *
 * @author Ghiz LOTFI
 */
@ManagedBean(name="userBean")
@ViewScoped
public class UserBean implements Serializable {
	
	private List<User> listUsers;
    private User user;
    private UserRole userRole;
	private String mdp;
	private String[] roles;
		
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{userServiceImpl}")
    private UserManager userManager;
	   
    @ManagedProperty(value="#{userRoleServiceImpl}")
    private UserRoleManager userRoleManager;
    
    @PostConstruct
    public void init(){
        	this.setUser(new User());
        	this.setUserRole(new UserRole());
            listUsers = new ArrayList<User>();
            try {
				listUsers.addAll(getUserManager().listUsers());
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
		
    public void addUser() throws Exception{
    	boolean test=false;
    	// on vérifie si l'user n'existe pas dans la bd
    	for(User c:listUsers){
		  if(c.getUsername().equals(user.getUsername())){
			  test=true;
		  }
    	}
    	if(!test)
		{
    		// on vérifie si les mdp sonts identiques
    		if(user.getPassword().equals(getMdp())){
    			// on vérifie si l'user a spécifié au moins un role 
    			if(roles.length!=0){
	    			try {
	    		        //Create MessageDigest object for MD5
	    		        MessageDigest digest = MessageDigest.getInstance("MD5");
	    		         
	    		        //Update input string in message digest
	    		        digest.update(getMdp().getBytes(), 0, getMdp().length());
	    		 
	    		        //Converts message digest value in base 16 (hex) 
	    		        user.setPassword(new BigInteger(1, digest.digest()).toString(16)); 
	    		 
	    		        } catch (NoSuchAlgorithmException e) {
	    		 
	    		            e.printStackTrace();
	    		        }
	    			user.setEnabled(UserStatus.ACTIVE);
	    			getUserManager().addUser(user);
	    			for(String role :roles){
	    				//on enregistre user s'il a comme role admin
    					if(role.equals("admin")){
    						getUserRole().setUser(getUserManager().findByUserName(getUser().getUsername()));
    						getUserRole().setRole("ROLE_ADMIN");
    						getUserRoleManager().addUserRole(userRole);
    						setUserRole(new UserRole());
    					}
    					// on enregistre user s'il a comme role user
    					if(role.equals("user")){
    						this.getUserRole().setUser(getUserManager().findByUserName(getUser().getUsername()));
    						System.out.println("test "+getUserRole().getUser().getUsername());
    						getUserRole().setRole("ROLE_USER");
    						getUserRoleManager().addUserRole(this.getUserRole());
    						setUserRole(new UserRole());
    					}
    				}
	    			listUsers.add(user);
	    			this.setUser(new User());
	    			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("L'utilisateur a été bien entregistré! "));
    			}
    			else{
    				FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Choisissez un role! "));
    			}
    		}
    		else{
    			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Les mots de passe sont pas identiques! "));
    		}
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("L'utilisateur existe déjà! "));
		}
    }
	  
	public void selectUser(int id){
		try {
			setUser(getUserManager().findByUserId(id));
			System.out.println("test0 "+getUser().getUsername() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void deleteUser() throws Exception{
    	// cette funct permet de supprimer l'user et son role
//    	System.out.println("test000 "+getUser().getUsername() );
//    	System.out.println("testazer "+getUser().getId());
    	for(UserRole userR:userRoleManager.listUsers()){
    		System.out.println("test "+userR.getUser().getUsername()+"test 1 "+getUser().getUsername());
    		if(userR.getUser().getUsername().equals(getUser().getUsername())){
    			if(userR.getRole().equals("ROLE_ADMIN")){
    				userRoleManager.deleteUserRole(userR);
    			}
    			if(userR.getRole().equals("ROLE_USER")){
    				userRoleManager.deleteUserRole(userR);
    			}
    		}
    	}
    	userManager.deleteUser(user);
    	listUsers.remove(user);
    	listUsers= new ArrayList<User>();
    	listUsers.addAll(getUserManager().listUsers());
    	this.setUser(new User());
    }
	   	    
    public void updateUser() throws Exception{
    	// pour modifier le user
    	System.out.println("test000 "+getUser().getUsername() );
    	userManager.updateUser(user);
		    	    
		for(User c:listUsers){
		    if(c.getUsername() == user.getUsername()){
		    			
		    	c.setPassword(user.getPassword());
		    }	    		
		}
		this.setUser(new User());
    }
    
    public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getSuccess() {
	return SUCCESS;
    }

    public static String getError() {
	return ERROR;
    }

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserRoleManager getUserRoleManager() {
		return userRoleManager;
	}

	public void setUserRoleManager(UserRoleManager userRoleManager) {
		this.userRoleManager = userRoleManager;
	}
    
}
