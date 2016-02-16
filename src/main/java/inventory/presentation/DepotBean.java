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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import inventory.model.Depot;
import inventory.service.depot.DepotManager;


@ManagedBean(name="depotBean")
@ViewScoped
public class DepotBean implements Serializable {
	
    private List<Depot> listDepots;
    private Depot depot;
	
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{depotServiceImpl}")
    private DepotManager depotManager;
	    
	 
	    
    @PostConstruct
    public void init(){
        try {
            depot = new Depot();
            listDepots = new ArrayList<Depot>();
            listDepots.addAll(getDepotManager().listDepots());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDepot() throws Exception{
    	// cette funct ajoute un dépot si son nom n'existe pas déja dans la bd
    	boolean test=false;
    	for(Depot c:listDepots){
		  if(c.getNomDepot().equals(depot.getNomDepot())){
			  test=true;
		  }
    	}
    	if(!test)
		{
		 	getDepotManager().addDepot(depot);
        	listDepots.add(depot);
    		setDepot(new Depot());
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nouveau dépot a été bien entregistré! "));
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nom du dépot existe déjà! "));
		}
    }
	   
	public void selectDepot(int id){
		// cette funct récupère le depot séléctioné par son id
		try {
			System.out.println(id);
			setDepot(getDepotManager().getDepotById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void deleteDepot() throws Exception{
    	// cette function supprime un depot de la bd
    	depotManager.deleteDepot(depot.getId());
    	listDepots.remove(depot);
    	listDepots = new ArrayList<Depot>();
        listDepots.addAll(getDepotManager().listDepots());
        setDepot(new Depot());
    }
    
    public void updateDepot() throws Exception{
    	// pour modifier le depot
    	depotManager.updateDepot(depot);
		    	    
		for(Depot c:listDepots){
		    if(c.getId() == depot.getId()){
		    			
		    	c.setNomDepot(depot.getNomDepot());
		    	c.setDescription(depot.getDescription());
		    	if(c.getAdresse()!= depot.getAdresse())
		    	{
		    		c.setAdresse(depot.getAdresse());
		    		c.setRayon(0.0);
			    	c.setLatitude(0.0);
			    	c.setLongitude(0.0);
		    	}
		    }	    		
		}
		depot = new Depot();
    }
    
	public List<Depot> getListDepots() {
		return listDepots;
	}

	public void setListDepots(List<Depot> listDepots) {
		this.listDepots = listDepots;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public DepotManager getDepotManager() {
		return depotManager;
	}

	public void setDepotManager(DepotManager depotManager) {
		this.depotManager = depotManager;
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
