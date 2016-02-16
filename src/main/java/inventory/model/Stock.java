/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ghiz LOTFI
 */
@Entity
@Table(name="STOCKS")
public class Stock implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nomStock;
    private Depot depot;
    private boolean modeAlerte;
    private Set<Product> Produits= new HashSet<Product>();
    private Set<Alerte> Alertes= new HashSet<Alerte>();
    private Set<Inventaire> Inventaires= new HashSet<Inventaire>();
    
    public Stock() {
    }
    
    public Stock(int id) {
        this.id = id;
    }

	public Stock(String nomStock, Depot depot, boolean modeAlerte) {
		super();
		this.nomStock = nomStock;
		this.depot = depot;
		this.modeAlerte = modeAlerte;
	}

	public Stock(String nomStock, Depot depot,boolean modeAlerte, Set<Product> produits) {
		super();
		this.nomStock = nomStock;
		this.depot = depot;
		this.modeAlerte = modeAlerte;
		Produits = produits;
	}
	
	public Stock(String nomStock, Depot depot,boolean modeAlerte, Set<Product> produits, Set<Alerte> alertes) {
		this.nomStock = nomStock;
		this.depot = depot;
		this.modeAlerte = modeAlerte;
		Produits = produits;
		Alertes = alertes;
	}
	
	public Stock(String nomStock, Depot depot, boolean modeAlerte, Set<Product> produits, Set<Alerte> alertes,
			Set<Inventaire> inventaires) {
		super();
		this.nomStock = nomStock;
		this.depot = depot;
		this.modeAlerte = modeAlerte;
		Produits = produits;
		Alertes = alertes;
		Inventaires = inventaires;
	}
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="NOMSTOCK", unique=true, nullable=false,length=25)
    public String getNomStock() {
		return nomStock;
	}
    
	public void setNomStock(String nomStock) {
		this.nomStock = nomStock;
	}
	
	@Column(name="MODEALERTE", nullable=false)
    public boolean isModeAlerte() {
		return modeAlerte;
	}

	public void setModeAlerte(boolean modeAlerte) {
		this.modeAlerte = modeAlerte;
	}

	@ManyToOne
    @JoinColumn(name = "DEPOT_ID", nullable = true)
    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
    
    @OneToMany(mappedBy = "stock")
    public Set<Product> getProduits() {
		return Produits;
	}

	public void setProduits(Set<Product> produits) {
		Produits = produits;
	}

	@OneToMany(mappedBy = "stock")
    public Set<Alerte> getAlertes() {
        return Alertes;
    }

    public void setAlertes(Set<Alerte> Alertes) {
        this.Alertes = Alertes;
    }

    @OneToMany(mappedBy = "stock")
    public Set<Inventaire> getInventaires() {
        return Inventaires;
    }

    public void setInventaires(Set<Inventaire> Inventaires) {
        this.Inventaires = Inventaires;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}



