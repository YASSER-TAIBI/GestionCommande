/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Table;


/**
 *
 * @author Hp
 */

@Entity
@Table(name = "PRIX_DIM_FOUR")
@NamedQuery(name="PrixDimFour.findAll", query="SELECT u FROM PrixDimFour u")
public class PrixDimFour implements Serializable{
    private static final long serialVersionUID = 1L;
        
        
        private int id;
    
        
	private MatierePremier matierePremiern = new MatierePremier() ;
   
       
	private Fournisseur fournisseur= new Fournisseur();
    
        
	private Dimension dimension= new Dimension();
      
      
        private BigDecimal prix ;
        
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    
    private Boolean action;

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }



    public PrixDimFour() {
    }


    
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
@ManyToOne
	@JoinColumn(name="MATIERE_PREMIER")
    public MatierePremier getMatierePremiern() {
        return matierePremiern;
    }

    public void setMatierePremiern(MatierePremier matierePremiern) {
        this.matierePremiern = matierePremiern;
    }

 
 @ManyToOne
	@JoinColumn(name="FOURNISSEUR")
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

@ManyToOne
	@JoinColumn(name="DIMENSION")
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

  @Column(name="PRIX")
    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

  
      
      
      
}
