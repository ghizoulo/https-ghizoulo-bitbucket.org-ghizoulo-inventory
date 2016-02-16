/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ghiz LOTFI
 */
@Entity
@Table(name="DEPOTS")
public class Depot implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id;
    private String nomDepot;
    private String description;
    private String adresse;
    private double rayon;
    private double latitude;
    private double longitude;
    private Set<Stock> stocks= new HashSet<Stock>();
   // private Set<Transaction> transactions= new HashSet<Transaction>();

    public Depot() {
    }

    public Depot(int id, String nomDepot, String description, String adresse) {
        this.id = id;
        this.nomDepot = nomDepot;
        this.description = description;
        this.adresse = adresse;
    }

    public Depot(String nomDepot, String description, String adresse) {
		this.nomDepot = nomDepot;
		this.description = description;
		this.adresse = adresse;
	}

	public Depot(int id, String nomDepot, String description, String adresse, double rayon, double latitude, double longitude) {
        this.id = id;
        this.nomDepot = nomDepot;
        this.description = description;
        this.adresse = adresse;
        this.rayon = rayon;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Depot(String nomDepot, String description, String adresse, double rayon, double latitude, double longitude) {
        this.nomDepot = nomDepot;
        this.description = description;
        this.adresse = adresse;
        this.rayon = rayon;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Depot(String nomDepot, String description, String adresse, double rayon, double latitude, double longitude, Set<Stock> stocks) {
        this.nomDepot = nomDepot;
        this.description = description;
        this.adresse = adresse;
        this.rayon = rayon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stocks = stocks;
    }
/*
    public Depot(String nomDepot, String description, double rayon, double latitude, double longitude, String note, Set<Stock> stocks,Set<Transaction> transactions) {
        this.nomDepot = nomDepot;
        this.description = description;
        this.rayon = rayon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.note = note;
        this.stocks = stocks;
        this.transactions = transactions;
    }
*/
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="NOMDEPOT", unique=true, nullable=false, length=25)
    public String getNomDepot() {
        return nomDepot;
    }

    public void setNomDepot(String nomDepot) {
        this.nomDepot = nomDepot;
    }

    @Column(name="DESCRIPTION", nullable=false, length=100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="ADRESSE", nullable=false, length=40)
    public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="RAYON", nullable=true)
    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Column(name="LATITUDE", nullable=true)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Column(name="LONGITUDE", nullable=true)
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @OneToMany(mappedBy = "depot")
    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
/*
    @OneToMany(mappedBy = "depot")
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
*/
    
}
