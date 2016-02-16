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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import inventory.model.AlienClass1ReaderTest;
import inventory.model.Famille;
import inventory.model.Product;
import inventory.model.Transaction;
import inventory.service.famille.FamilleManager;
import inventory.service.product.ProductManager;
import inventory.service.stock.StockManager;
import inventory.service.transaction.TransactionManager;

/**
 *
 * @author Ghiz LOTFI
 */
@ManagedBean(name="productBean")
@ViewScoped
public class ProductBean implements Serializable {
	
	private String message;
	private boolean afficheBloc;
	private boolean afficheStock;
    private List<Product> listProducts;
    private Product produit;
    private Famille famille;
    private Transaction transaction;
    private int idStock;
    private int idFamille;
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
	    
    @ManagedProperty(value="#{simpleProductManager}")
    private ProductManager productManager;
	    
    @ManagedProperty(value="#{familleServiceImpl}")
    private FamilleManager familleManager;
	    
    @ManagedProperty(value="#{stockServiceImpl}")
    private StockManager stockManager;
    
    @ManagedProperty(value="#{transactionServiceImpl}")
    private TransactionManager transactionManager;
    
    @PostConstruct
    public void init(){
        try {
            produit = new Product();
            listProducts = new ArrayList<Product>();
            listProducts.addAll(getProductManager().listProducts());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setFamille(new Famille());
        this.setAfficheBloc(false);
        this.setAfficheStock(false);
        this.setIdStock(0);
        this.setIdFamille(0);
        this.setTransaction(new Transaction());
    }
		
    public void updateSelectedValue() {
    	/*lors de la selection de la famille, cette funct modifier la var AfficheBloc
    	*	pour afficher la quantité et le prix moyen de la famille
    	*	ce traitement est fait pour gérer le probleme de primefaces avec selectonemenu
    	*/
        this.setAfficheBloc(true);
        try {
			this.setFamille(getFamilleManager().getFamilleById(getIdFamille()));
			this.getProduit().setFamille(getFamille());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void stockSelected(){
    	/* ce traitement aussi affiche la liste des stock si on clique
    	*	sur ce choix un bloc s'affiche à l'aide de la var AfficheStock
    	*/
    	this.setAfficheStock(true);
    }
    
    public void addProduit() throws Exception{
    	// pour l'ajout d'un produit
    	this.getProduit().setFamille(getFamille());
    	this.getProduit().setPrix(getFamille().getMoyenPrix());
    	this.getProduit().setQuantite(getFamille().getMoyenQuantite());
    	if(getIdStock() != 0){
    		this.getProduit().setStock(getStockManager().getStockById(getIdStock()));
    	}
     	// set product to the database
        this.getProductManager().addProduct(produit);
        // set product to the list
        this.listProducts.add(produit);
        // set a new in transaction
        this.getTransaction().setTypeTransaction("Transaction d'entrée");
        this.getTransaction().setDateTransaction(new Date());
        this.getTransaction().setDescription("Ce produit a été bien ajouté au stock! ");
        this.getTransaction().getProduits().add(getProduit());
        this.getTransactionManager().addTransaction(getTransaction());
        
        this.setProduit(new Product());
        this.setFamille(new Famille());
        this.setAfficheBloc(false);
        this.setAfficheStock(false);
        this.setIdStock(0);
        FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Le nouveau produit a été bien entregistré! "));
    }
	public void selectProduit(int id){
		// selectionne le produit choisi
		try {
			setProduit(getProductManager().getProductById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectProduitUpdate(int id){
		// selectionne le produit choisi et la famille du produit
		try {
			this.setProduit(getProductManager().getProductById(id));
			this.setFamille(getProduit().getFamille());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(getProduit().getStock().getId() == 0)
			{
				this.setIdStock(0);
			}
			else
			{
				try {
					this.setIdStock(getProduit().getStock().getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
    public void deleteProduit() throws Exception{
    	// supprime un produit
        productManager.deleteProduct(produit.getId());
        listProducts.remove(produit);
        listProducts = new ArrayList<Product>();
        listProducts.addAll(getProductManager().listProducts());
        this.setProduit(new Product());
        this.setFamille(new Famille());
        this.setAfficheBloc(false);
        this.setIdStock(0);
    }
	   	    
    public void updateProduit() throws Exception{
    	// pour modifier le produit
    	this.getProduit().setFamille(getFamille());
    	try {
			if(getIdStock() != 0)
			{
				try {
					this.getProduit().setStock(getStockManager().getStockById(getIdStock()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		productManager.updateProduct(produit);
		    	    
		for(Product c:listProducts){
		    if(c.getId() == produit.getId()){		
		    	c.setIdTag(produit.getIdTag());
	            c.setLibelle(produit.getLibelle());
	         	c.setDesignation(produit.getDesignation());
	         	c.setFournisseur(produit.getFournisseur());
	         	c.setFamille(produit.getFamille());
	         	c.setPrix(getFamille().getMoyenPrix());
	         	c.setQuantite(getFamille().getMoyenQuantite());
	         	c.setStock(produit.getStock());
		    }	    		
		}
		this.setProduit(new Product());
	 	this.setFamille(new Famille());
	 	this.setAfficheBloc(false);
	 	this.setIdStock(0);
    }

    public void getTAG() throws Exception{
    	// pour récupérer le tag du lecteur a laide de la fct AlienClassReaderTest
    	if(AlienClass1ReaderTest.getIDTag().equals("0"))
    	{
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("No tag found"));
    		produit.setIdTag("");
    	}
    	else if(AlienClass1ReaderTest.getIDTag().equals("1"))
    	{
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("More than one tag found"));
    		produit.setIdTag("");
    	}
    	else{
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("One tag found"));
    		produit.setIdTag(AlienClass1ReaderTest.getIDTag());
    	}
//    	if(AlienClass1ReaderTest.getIds().isEmpty()){
//    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("No tag found"));
//    	}
//    	else if(AlienClass1ReaderTest.getIds().size() > 1){
//    		System.out.println("0000000");
//    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("Tags found"));
//    	}
//    	else{
//    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("tag found"));
//    		produit.setIdTag(AlienClass1ReaderTest.getIds().get(0));
//    	}
    }

    public void addStock(){
    	// pour ajouter un produit au stock
    	try {
			getProduit().setStock(getStockManager().getStockById(getIdStock()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void updateStockP(){
    	//pour modifier le stock du produit
    	try {
			System.out.println("test "+getProduit());
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
    	try {
			this.getProduit().setStock(getStockManager().getStockById(idStock));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			productManager.updateProduct(getProduit());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		    	    
		for(Product c:listProducts){
		    if(c.getId() == produit.getId()){
		    	try {
					c.setStock(produit.getStock());
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }	    		
		}
		produit = new Product();
		this.setIdStock(0);
    }
    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public Product getProduit() {
        return produit;
    }

    public void setProduit(Product produit) {
        this.produit = produit;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
	
	public FamilleManager getFamilleManager() {
		return familleManager;
	}

	public void setFamilleManager(FamilleManager familleManager) {
		this.familleManager = familleManager;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public boolean isAfficheBloc() {
		return afficheBloc;
	}

	public void setAfficheBloc(boolean afficheBloc) {
		this.afficheBloc = afficheBloc;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public StockManager getStockManager() {
		return stockManager;
	}

	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}

	public boolean isAfficheStock() {
		return afficheStock;
	}

	public void setAfficheStock(boolean afficheStock) {
		this.afficheStock = afficheStock;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public int getIdFamille() {
		return idFamille;
	}

	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
	}
    
}
