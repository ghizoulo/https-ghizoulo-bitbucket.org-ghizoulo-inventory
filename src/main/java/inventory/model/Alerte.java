/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ghiz LOTFI
 */
@Entity
@Table(name="ALERTES")
public class Alerte implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeAlerte;
    private Date dateAlerte;
    private Date dateArret;
	private int nbrMinimum;
	private int nbrMaximum;
    private Stock stock;
//    private Product produit;
    
    public Alerte() {
    }

    public Alerte(int id, String typeAlerte, Date dateArret) {
		this.id = id;
		this.typeAlerte = typeAlerte;
		this.dateArret = dateArret;
	}

	public Alerte(String typeAlerte, Date dateAlerte) {
		super();
		this.typeAlerte = typeAlerte;
		this.dateAlerte = dateAlerte;
	}

	public Alerte(String typeAlerte, int nbrMinimum, int nbrMaximum, Date dateAlerte, Stock stock) {
		this.typeAlerte = typeAlerte;
		this.nbrMinimum = nbrMinimum;
		this.nbrMaximum = nbrMaximum;
		this.dateAlerte = dateAlerte;
		this.stock = stock;
	}
    
//    public Alerte(String typeAlerte, Date dateAlerte, Stock stock, Product produit) {
//		this.typeAlerte = typeAlerte;
//		this.dateAlerte = dateAlerte;
//		this.stock = stock;
//		this.produit = produit;
//	}

    public Alerte(int id, Date dateArret) {
		this.id = id;
		this.dateArret = dateArret;
	}
    
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="TYPEALERTE", nullable=false, length=25)
    public String getTypeAlerte() {
        return typeAlerte;
    }

    public void setTypeAlerte(String typeAlerte) {
        this.typeAlerte = typeAlerte;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DATEALERTE", nullable=false)
    public Date getDateAlerte() {
        return dateAlerte;
    }

    public void setDateAlerte(Date dateAlerte) {
        this.dateAlerte = dateAlerte;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATEARRET", nullable=true)
    public Date getDateArret() {
        return dateArret;
    }

    public void setDateArret(Date dateArret) {
        this.dateArret = dateArret;
    }

    @Column(name="NBRMINIMUM", nullable=true)
    public int getNbrMinimum() {
		return nbrMinimum;
	}

	public void setNbrMinimum(int nbrMinimum) {
		this.nbrMinimum = nbrMinimum;
	}

	@Column(name="NBRMAXIMUM", nullable=true)
	public int getNbrMaximum() {
		return nbrMaximum;
	}

	public void setNbrMaximum(int nbrMaximum) {
		this.nbrMaximum = nbrMaximum;
	}

	@ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

//    @ManyToOne(targetEntity = Stock.class)
//    @JoinColumn(name = "PRODUCT_ID", nullable = true)
//	public Product getProduit() {
//		return produit;
//	}
//
//	public void setProduit(Product produit) {
//		this.produit = produit;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
    
}