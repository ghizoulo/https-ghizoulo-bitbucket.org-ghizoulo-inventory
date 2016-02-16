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
@Table(name="INVENTAIRES")
public class Inventaire implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id;
    private Date dateInventaire;
    private String descriptionI;
    private String responsableI;
    private Stock stock;
    
    public Inventaire() {
    }

    public Inventaire(int id, Date dateInventaire, String descriptionI, String responsableI) {
        this.id = id;
        this.dateInventaire = dateInventaire;
        this.descriptionI = descriptionI;
        this.responsableI = responsableI;
    }

    public Inventaire(Date dateInventaire, String descriptionI, String responsableI) {
        this.dateInventaire = dateInventaire;
        this.descriptionI = descriptionI;
        this.responsableI = responsableI;
    }

    public Inventaire(Date dateInventaire, String descriptionI, String responsableI, Stock stock) {
        this.dateInventaire = dateInventaire;
        this.descriptionI = descriptionI;
        this.responsableI = responsableI;
        this.stock = stock;
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

    @Temporal(TemporalType.DATE)
    @Column(name="DATEINVENTAIRE", unique=true, nullable=false)
    public Date getDateInventaire() {
        return dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }

    @Column(name="DESCRIPTIONI", nullable=false, length=100)
    public String getDescriptionI() {
        return descriptionI;
    }

    public void setDescriptionI(String descriptionI) {
        this.descriptionI = descriptionI;
    }

    @Column(name="RESPONSABLEI", nullable=false, length=25)
    public String getResponsableI() {
        return responsableI;
    }

    public void setResponsableI(String responsableI) {
        this.responsableI = responsableI;
    }

    @ManyToOne(targetEntity = Stock.class)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    
            
}
