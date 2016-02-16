/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ghiz LOTFI
 */
@Entity
@Table(name="TRANSACTIONS")
public class Transaction implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id;
    private Date dateTransaction;
    private String typeTransaction;
    private String description;
    private Set<Product> produits= new HashSet<Product>();

    public Transaction() {
    }
    
    public Transaction(int id, Date dateTransaction, String typeTransaction, String description, Set<Product> produits) {
		this.id = id;
		this.dateTransaction = dateTransaction;
		this.typeTransaction = typeTransaction;
		this.description = description;
		this.produits = produits;
	}

	public Transaction(Date dateTransaction, String typeTransaction, String description, Set<Product> produits) {
		this.id = -1;
		this.dateTransaction = dateTransaction;
		this.typeTransaction = typeTransaction;
		this.description = description;
		this.produits = produits;
	}

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRANSACTION_ID", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DATETRANSACTION", nullable=false)
    public Date getDateTransaction() {
		return dateTransaction;
	}
    
	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

    @Column(name="TYPETRANSACTION", nullable=false, length=25)
    public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	
    @Column(name="DESCRIPTION", nullable=true, length=100)
    public String getDescription() {
		return description;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "transactions")
	public Set<Product> getProduits() {
		return produits;
	}

	public void setProduits(Set<Product> produits) {
		this.produits = produits;
	}
    
}
