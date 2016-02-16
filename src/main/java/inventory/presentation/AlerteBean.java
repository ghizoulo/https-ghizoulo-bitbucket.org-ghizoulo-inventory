/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import inventory.model.Alerte;
import inventory.model.Stock;
import inventory.service.alerte.AlerteManager;
import inventory.service.stock.StockManager;

/**
 *
 * @author Ghiz LOTFI
 * 
 * ce  JavaBean pour interagir avec l'interface en utilisant JSF et primeFaces
 * il permet d'ajouter, modifier et supprimer une alerte et affiche aussi la liste des alertes 
 */
@ManagedBean(name="alerteBean")
@SessionScoped
public class AlerteBean implements Serializable {
	
	//private String message;
    private List<Alerte> listAlertes;
    private Alerte alerte;
    private Stock stock;
    private boolean afficheBloc;
    private int idStock;
    private int idProduit;
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{alerteServiceImpl}")
    private AlerteManager alerteManager;
	    
    @ManagedProperty(value="#{stockServiceImpl}")
    private StockManager stockManager;	    
    
    @PostConstruct
    public void init(){
    	// initialisation des variables
        try {
            alerte = new Alerte();
            listAlertes = new ArrayList<Alerte>();
            listAlertes.addAll(getAlerteManager().listAlertes());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setAfficheBloc(false);
        this.setIdStock(0);
        this.setIdProduit(0);
        this.setStock(new Stock());
    }
		
    public void addAlerte() throws Exception{
    	// on vérifie si les champs nbrMaximum et nbrMinimum sont valides
    	if(getAlerte().getNbrMaximum() != 0 && getAlerte().getNbrMaximum() <= getAlerte().getNbrMinimum()){
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nombre maximum doit étre plus grand que le nombre minimum! "));
    	}
    	else if(getAlerte().getNbrMinimum() != 0){
    	// on récupère les données saisies dans l'objet Alerte
    		try{
    			getAlerte().setTypeAlerte("AlerteStock");
        		getAlerte().setStock(getStockManager().getStockById(getIdStock()));
        		getAlerte().setDateAlerte(new Date());
        		// on ajoute l'objet Alerte à la BD
        		getAlerteManager().addAlerte(alerte);
        	} catch (Exception ex) {
                Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    		// on récupère le stock choisi par son id
    		this.setStock(getStockManager().getStockById(getIdStock()));
    		// on modifie le champs modeAlerte dans l'objet Stock
    		this.getStock().setModeAlerte(true);
    		// on modifie l'objet Stock dans la bd  
    		this.getStockManager().updateStock(getStock());
    		// l'alerte ajouté aussi à la liste des alertes exportée de la bd
        	listAlertes.add(alerte);
        	//un message de confirmation est affiché
        	FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("L'alerte zone a été bien enregistrée! "));
    	}
    	// la réinitialisation des variables
    	setAlerte(new Alerte());
    	this.setStock(new Stock());
    	this.setIdStock(0);
    }
    
    //la fonction récupère l'id du l'objet alerte choisi
	public void selectAlerte(int id){
		try {
			//on initialise l'objet alerte avec celui-ci choisi
			setAlerte(getAlerteManager().getAlerteById(id));
			//on récupère le id du stock de l'alerte
			setIdStock(getAlerte().getStock().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void deleteAlerte() throws Exception{
    	// cette funct supprime l'alerte de la bd
    	getAlerteManager().deleteAlerte(alerte.getId());
    	this.setStock(getStockManager().getStockById(alerte.getStock().getId()));
    	// on modifie le champs modeAlerte du stock dont son alerte est supprimé
    	this.getStock().setModeAlerte(false);
    	this.getStockManager().updateStock(getStock());
    	// initialisation des var
    	listAlertes.remove(alerte);
    	listAlertes = new ArrayList<Alerte>();
    	listAlertes.addAll(getAlerteManager().listAlertes());
    	setAlerte(new Alerte());
    	setStock(new Stock());
    }
	   	    
    public void updateAlerte() throws Exception{
    	if(getAlerte().getNbrMaximum() != 0 && getAlerte().getNbrMaximum() <= getAlerte().getNbrMinimum()){
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nombre maximum doit étre plus grand que le nombre minimum! "));
    	}
    	else if(getAlerte().getNbrMinimum() != 0){
	    	// on modifie l'alerte
	    	getAlerteManager().updateAlerte(alerte);
	    	
			for(Alerte c:listAlertes){
			    if(c.getId() == alerte.getId()){
			    			
			    	c.setTypeAlerte(alerte.getTypeAlerte());
			    	c.setDateAlerte(alerte.getDateArret());
			    	c.setNbrMinimum(alerte.getNbrMinimum());
			    	c.setNbrMaximum(alerte.getNbrMaximum());
			    	c.setStock(alerte.getStock());
			    }	    		
			}
    	}
		alerte = new Alerte();
    }


	public List<Alerte> getListAlertes() {
		return listAlertes;
	}

	public void setListAlertes(List<Alerte> listAlertes) {
		this.listAlertes = listAlertes;
	}

	public Alerte getAlerte() {
		return alerte;
	}

	public void setAlerte(Alerte alerte) {
		this.alerte = alerte;
	}

	public AlerteManager getAlerteManager() {
		return alerteManager;
	}

	public void setAlerteManager(AlerteManager alerteManager) {
		this.alerteManager = alerteManager;
	}

//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getSuccess() {
	return SUCCESS;
    }

    public static String getError() {
	return ERROR;
    }

	public boolean isAfficheBloc() {
		return afficheBloc;
	}

	public void setAfficheBloc(boolean afficheBloc) {
		this.afficheBloc = afficheBloc;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public StockManager getStockManager() {
		return stockManager;
	}

	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
    
}
