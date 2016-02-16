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
import inventory.service.depot.DepotManager;
import inventory.service.stock.StockManager;

@ManagedBean(name="stockBean")
@SessionScoped
public class StockBean implements Serializable {
	
    private List<Stock> listStocks;
    private List<Stock> listStocksAlertes;
    private Stock stock;
    private Alerte alerte;
    private int idDepot;

	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{stockServiceImpl}")
    private StockManager stockManager;
	    
	@ManagedProperty(value="#{depotServiceImpl}")
	private DepotManager depotManager;
	
	@ManagedProperty(value="#{alerteServiceImpl}")
    private AlerteManager alerteManager;

	@PostConstruct
    public void init(){
        try {
            stock = new Stock();
            listStocks = new ArrayList<Stock>();
            listStocks.addAll(getStockManager().listStocks());
            setListStocksAlertes(new ArrayList<Stock>());
            for(Stock s:listStocks){
            	if(!s.isModeAlerte()){
            		listStocksAlertes.add(s);
            	}
            }
            //System.out.println("test "+listStocksAlertes.size());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIdDepot(0);
        this.setAlerte(new Alerte());
    }
	
    public void addStock() throws Exception{
    	// pour ajouter un stock
    	stock.setModeAlerte(false);
    	this.getStock().setDepot(getDepotManager().getDepotById(getIdDepot()));
    	getStockManager().addStock(stock);
    	listStocks.add(stock);
    	setStock(new Stock());
    	this.setIdDepot(0);
    	FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nouveau stock a été bien entregistré! "));
    }
    
    public void selectStock(int id) throws Exception{
    	//pour selectionner le stock
    	try {
			setStock(getStockManager().getStockById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	this.setIdDepot(this.getStock().getDepot().getId());
    }
    
    public void selectStockAlerte(int id){
    	// selectionne le stock pour désactiver son alerte
    	try {
			setStock(getStockManager().getStockById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	this.setIdDepot(this.getStock().getDepot().getId());
    	this.getStock().setModeAlerte(false);
    	try {
			getStockManager().updateStock(getStock());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	for(Stock c:listStocks){
		    if(c.getId() == stock.getId()){	
		    	c.setModeAlerte(false);
		    }	    		
		}
    	try {
    		for(Alerte a:getAlerteManager().listAlertes()){
    			if(a.getStock().getId() == getStock().getId()){
    				if(a.getDateArret() == null){
    					this.setAlerte(a);
        				this.getAlerte().setDateArret(new Date());
        	    		this.getAlerteManager().updateAlerte(getAlerte());
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void deleteStock() throws Exception{
    	// pour supprimer le stock
    	getStockManager().deleteStock(stock.getId());
    	listStocks.remove(stock);
    	listStocks = new ArrayList<Stock>();
    	listStocks.addAll(getStockManager().listStocks());
    	setStock(new Stock());
    }
	   	    
    public void updateStock() throws Exception{
    	// pour modifier le stock
    	this.getStock().setDepot(getDepotManager().getDepotById(getIdDepot()));
    	stockManager.updateStock(stock);
		for(Stock c:listStocks){
		    if(c.getId() == stock.getId()){	
		    	c.setNomStock(stock.getNomStock());
		    	c.setDepot(getDepotManager().getDepotByName(stock.getDepot().getNomDepot()));
		    }	    		
		}
		stock = new Stock();
		this.setIdDepot(0);
    }
    
    public void addAlerte(){
    	// pour ajouter l'alerte au stock
    	this.getStock().setModeAlerte(true);
    	try {
			stockManager.updateStock(stock);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for(Stock c:listStocks){
		    if(c.getId() == stock.getId()){	
		    	c.setModeAlerte(true);
		    }	    		
		}
    	if(getAlerte().getNbrMaximum() != 0 && getAlerte().getNbrMaximum() <= getAlerte().getNbrMinimum()){
    		FacesContext.getCurrentInstance().addMessage("formDialogAlerte:newPassword1Error", new FacesMessage("Le nombre maximum doit étre plus grand que le nombre minimum! "));
    	}
    	else{
    		try{
    			getAlerte().setTypeAlerte("AlerteStock");
    			this.getAlerte().setStock(getStock());
        		getAlerte().setDateAlerte(new Date());
        		getAlerteManager().addAlerte(getAlerte());
        	} catch (Exception ex) {
                Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    	}
    	this.setAlerte(new Alerte());
    	stock = new Stock();
    	this.setIdDepot(0);
    }
    
	public List<Stock> getListStocks() {
		return listStocks;
	}

	public void setListStocks(List<Stock> listStocks) {
		this.listStocks = listStocks;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public StockManager getStockManager() {
		return stockManager;
	}

	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
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
    
	public int getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(int idDepot) {
		this.idDepot = idDepot;
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

	public List<Stock> getListStocksAlertes() {
		return listStocksAlertes;
	}

	public void setListStocksAlertes(List<Stock> listStocksAlertes) {
		this.listStocksAlertes = listStocksAlertes;
	}
    
}
