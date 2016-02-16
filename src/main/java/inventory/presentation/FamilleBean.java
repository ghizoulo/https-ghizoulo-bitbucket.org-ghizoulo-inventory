package inventory.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import inventory.model.Famille;
import inventory.service.famille.FamilleManager;

@ManagedBean(name="familleBean")
@SessionScoped
public class FamilleBean implements Serializable {
	
    private List<Famille> listFamilles;
    private Famille famille;
	
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{familleServiceImpl}")
    private FamilleManager familleManager;
	    
    private int id;
    
    @PostConstruct
    public void init(){
        try {
        	this.setFamille(new Famille());
            listFamilles = new ArrayList<Famille>();
            listFamilles.addAll(getFamilleManager().listFamilles());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
		
    public void addFamille() throws Exception{
    	// ajout d'une nouvelle famille dont le nom nexiste pas deja dans la bd
    	boolean test=false;
    	for(Famille c:listFamilles){
		  if(c.getNomFamille().equals(famille.getNomFamille())){
			  test=true;
		  }
    	}
    	if(!test)
		{
		 	getFamilleManager().addFamille(famille);
			listFamilles.add(famille);
			this.setFamille(new Famille());
			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("La famille a été bien entregistrée! "));
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nom de famille existe déjà! "));
		}
    }
	  
	public void selectFamille(int id){
		// récupère la famille avec son id
		try {
			setFamille(getFamilleManager().getFamilleById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void deleteFamille() throws Exception{
    	//supprime la famille
    	familleManager.deleteFamille(famille.getId());
    	listFamilles.remove(famille);
    	listFamilles= new ArrayList<Famille>();
    	listFamilles.addAll(getFamilleManager().listFamilles());
    	this.setFamille(new Famille());
    }
	   	    
    public void updateFamille() throws Exception{
    	// modifier la famille
    	familleManager.updateFamille(famille);
		    	    
		for(Famille c:listFamilles){
		    if(c.getId() == famille.getId()){
		    			
		    	c.setMoyenPrix(famille.getMoyenPrix());
		    	c.setMoyenQuantite(famille.getMoyenQuantite());
		    	c.setNomFamille(famille.getNomFamille());
		    	c.setDescriptionF(famille.getDescriptionF());
		    }	    		
		}
		this.setFamille(new Famille());
    }
    
    public List<Famille> getListFamilles() {
		return listFamilles;
	}

	public void setListFamilles(List<Famille> listFamilles) {
		this.listFamilles = listFamilles;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public FamilleManager getFamilleManager() {
		return familleManager;
	}

	public void setFamilleManager(FamilleManager familleManager) {
		this.familleManager = familleManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
    
}
