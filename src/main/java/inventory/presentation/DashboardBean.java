package inventory.presentation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import inventory.service.dashboard.DashboardManager;

@ManagedBean(name="dashboardBean")
@SessionScoped
public class DashboardBean {
	private Long nbrAlerte;
	private Long nbrProduits;
	private Long nbrFamilles;
	private Long nbrStocks;
	
	@ManagedProperty(value="#{dashboardServiceImpl}")
    private DashboardManager dashboardManager;
	
	@PostConstruct
	public void init(){
		try {
			nbrAlerte = dashboardManager.getAlertsNbr();
			nbrProduits = dashboardManager.getProductsNbr();
			nbrFamilles = dashboardManager.getFamillesNbr();
			nbrStocks = dashboardManager.getStocksNbr();
	 	} catch (Exception ex) {
	     	Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
	  	}
	}

	public DashboardManager getDashboardManager() {
		return dashboardManager;
	}

	public void setDashboardManager(DashboardManager dashboardManager) {
		this.dashboardManager = dashboardManager;
	}

	public Long getNbrAlerte() {
		return nbrAlerte;
	}

	public void setNbrAlerte(Long nbrAlerte) {
		this.nbrAlerte = nbrAlerte;
	}

	public Long getNbrProduits() {
		return nbrProduits;
	}

	public void setNbrProduits(Long nbrProduits) {
		this.nbrProduits = nbrProduits;
	}

	public Long getNbrFamilles() {
		return nbrFamilles;
	}

	public void setNbrFamilles(Long nbrFamilles) {
		this.nbrFamilles = nbrFamilles;
	}

	public Long getNbrStocks() {
		return nbrStocks;
	}

	public void setNbrStocks(Long nbrStocks) {
		this.nbrStocks = nbrStocks;
	}
	 
}
