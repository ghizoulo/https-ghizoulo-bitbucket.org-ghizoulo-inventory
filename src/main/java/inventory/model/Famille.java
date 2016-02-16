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
@Table(name="FAMILLES")
public class Famille implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id;
    private String nomFamille;
    private String descriptionF;
    private float moyenPrix;
    private float moyenQuantite;
    private Set<Product> Produits= new HashSet<Product>();
    
    
    public Famille() {
		super();
	}

	public Famille(int id) {
		super();
		this.id = id;
	}

	public Famille(String nomFamille, float moyenPrix, float moyenQuantite) {
		super();
		this.nomFamille = nomFamille;
		this.moyenPrix = moyenPrix;
		this.moyenQuantite = moyenQuantite;
	}
	
	public Famille(String nomFamille, String descriptionF, float moyenPrix, float moyenQuantite) {
		super();
		this.nomFamille = nomFamille;
		this.descriptionF = descriptionF;
		this.moyenPrix = moyenPrix;
		this.moyenQuantite = moyenQuantite;
	}
	
	public Famille(String nomFamille, float moyenPrix, float moyenQuantite, Set<Product> produits) {
		super();
		this.nomFamille = nomFamille;
		this.moyenPrix = moyenPrix;
		this.moyenQuantite = moyenQuantite;
		Produits = produits;
	}

	public Famille(String nomFamille, String descriptionF, float moyenPrix, float moyenQuantite,
			Set<Product> produits) {
		super();
		this.nomFamille = nomFamille;
		this.descriptionF = descriptionF;
		this.moyenPrix = moyenPrix;
		this.moyenQuantite = moyenQuantite;
		Produits = produits;
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

    @Column(name="NOMFAMILLE", nullable=false, length=25)
    public String getNomFamille() {
        return this.nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    @Column(name="DESCRIPTIONF", nullable=true, length=100)
    public String getDescriptionF() {
        return descriptionF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }

    @Column(name="MOYENPRIX", nullable=true)
    public float getMoyenPrix() {
		return moyenPrix;
	}

	public void setMoyenPrix(float moyenPrix) {
		this.moyenPrix = moyenPrix;
	}

	@Column(name="MOYENQUANTITE", nullable=true)
	public float getMoyenQuantite() {
		return moyenQuantite;
	}

	public void setMoyenQuantite(float moyenQuantite) {
		this.moyenQuantite = moyenQuantite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(mappedBy = "famille")
    public Set<Product> getProduits() {
        return Produits;
    }

    public void setProduits(Set<Product> Produits) {
        this.Produits = Produits;
    }
}
