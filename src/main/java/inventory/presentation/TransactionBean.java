package inventory.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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

import inventory.model.MessageListenerTest;
import inventory.model.Product;
import inventory.model.Transaction;
import inventory.service.product.ProductManager;
import inventory.service.transaction.TransactionManager;

@ManagedBean(name="transactionBean")
@ViewScoped
public class TransactionBean implements Serializable {
	
    private List<Transaction> listTransactions;
    private List<String> listTags;
    private List<Product> listProduits;
    private Transaction transaction;
    private int idProduit;
    private boolean afficheBlock;
	
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    private static List<String> listPorductTags;
    private static boolean arretTest; 
  
	    
    @ManagedProperty(value="#{transactionServiceImpl}")
    private TransactionManager transactionManager;
	    
    @ManagedProperty(value="#{simpleProductManager}")
    private ProductManager produitManager;
	    
    private int id;
    
    @PostConstruct
    public void init(){
        try {
        	this.setTransaction(new Transaction());
        	this.setListTags(new ArrayList<String>());
            listTransactions = new ArrayList<Transaction>();
            listTransactions.addAll(getTransactionManager().listTransactions());
        } catch (Exception ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
		this.setIdProduit(0);
		this.setAfficheBlock(false);
    }
	  
	public void selectTransaction(int id){
		try {
			setTransaction(getTransactionManager().getTransactionById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void deleteTransaction() throws Exception{
    	transactionManager.deleteTransaction(transaction.getId());
    	listTransactions.remove(transaction);
    	listTransactions= new ArrayList<Transaction>();
    	listTransactions.addAll(getTransactionManager().listTransactions());
    	this.setTransaction(new Transaction());
    }
	   	    
    public void updateTransaction() throws Exception{
//    	if(getIdProduit() != 0){
//    		this.getTransaction().setProduit(getProduitManager().getProductById(getIdProduit()));
//    	}
    	transactionManager.updateTransaction(transaction);
		    	    
		for(Transaction c:listTransactions){
		    if(c.getId() == transaction.getId()){
		    			
		    	c.setTypeTransaction(transaction.getTypeTransaction());
		    	c.setDateTransaction(transaction.getDateTransaction());
		    	//c.setProduit(transaction.getProduit());
		    	c.setDescription(transaction.getDescription());
		    }	    		
		}
		this.setTransaction(new Transaction());
    }
    
    public void lancerLecteur(){
    	// cette funct lance la classe MessageListenerTest pour récupérer les tags existants
    	try {
			new MessageListenerTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void arreterLecteur() throws Exception{
    	// pour arreter le lecteur et récupérer la liste des tags trouvés
    	List<Transaction> listTrans = new ArrayList<Transaction>();
    	TransactionBean.setArretTest(true);
    	this.setListTags(new ArrayList<String>());
    	if(TransactionBean.getListPorductTags().isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("No tag found! "));
    	}
    	else{
    		FacesContext.getCurrentInstance().addMessage("myForm:newPassword1Error", new FacesMessage("La liste des tags! "));
    		this.setListTags(TransactionBean.getListPorductTags());
    		this.setListProduits(new ArrayList<Product>());
    	}
    	this.setAfficheBlock(true);
    	try {
    		// on vérifie si les tags trouvés existe déja dans la bd
			for(Product p:getProduitManager().listProducts()){
				for(String tag:getListTags()){
					System.out.println("taaag= "+tag);
					if(p.getIdTag().equals(tag)){
						this.getListProduits().add(p);
					}
				}
			}
			System.out.println("size is "+getListProduits().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(getListProduits().isEmpty()){
    		System.out.println("echec ");
    	}
    	else{
    		// on ajoute cette transaction à une liste de transaction
    		listTrans.add(getTransaction());
    		this.getTransaction().setDateTransaction(new Date());
        	this.getTransaction().setTypeTransaction("Transaction out");
        	this.getTransaction().setDescription("ces produits sont sortis le :");
//        	getTransactionManager().addTransaction(getTransaction());
        	try{
        		Set<Product> listToSet= new HashSet<Product>(getListProduits());
            	this.getTransaction().setProduits(listToSet);
        	} catch (Exception e) {
    			e.printStackTrace();
    		}
        	try {
        		getTransactionManager().addTransaction(getTransaction());
//    			getTransactionManager().updateTransaction(getTransaction());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        	try{
        		// la liste des transactions est ajouté à la bd
	        	Set<Transaction> listTs = new HashSet<Transaction>(listTrans);
	        	for(Product p:getListProduits()){
	        		p.setTransactions(listTs);
	        		getProduitManager().updateProduct(p);
	        	}
        	} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    public List<Transaction> getListTransactions() {
		return listTransactions;
	}

	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
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

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public ProductManager getProduitManager() {
		return produitManager;
	}

	public void setProduitManager(ProductManager produitManager) {
		this.produitManager = produitManager;
	}

	public static boolean isArretTest() {
		return arretTest;
	}

	public static void setArretTest(boolean arretTest) {
		TransactionBean.arretTest = arretTest;
	}

	public boolean isAfficheBlock() {
		return afficheBlock;
	}

	public void setAfficheBlock(boolean afficheBlock) {
		this.afficheBlock = afficheBlock;
	}

	public static List<String> getListPorductTags() {
		return listPorductTags;
	}

	public static void setListPorductTags(List<String> listPorductTags) {
		TransactionBean.listPorductTags = listPorductTags;
	}

	public List<String> getListTags() {
		return listTags;
	}

	public void setListTags(List<String> listTags) {
		this.listTags = listTags;
	}

	public List<Product> getListProduits() {
		return listProduits;
	}

	public void setListProduits(List<Product> listProduits) {
		this.listProduits = listProduits;
	}
    
}
