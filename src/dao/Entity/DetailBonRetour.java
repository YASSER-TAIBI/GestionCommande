/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_BON_RETOUR")
@NamedQuery(name = "DetailBonRetour.findAll", query = "SELECT u FROM DetailBonRetour u")
public class DetailBonRetour implements Serializable {
     private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Prix_Unitaire")
    private BigDecimal prixUnitaire;
    
    @Column(name = "QuantiteRetour")
    private BigDecimal quantiteRetour ;

    @ManyToOne
    @JoinColumn(name = "id_mat_pre")
    private MatierePremier matierePremier;

    @Column(name = "Montant")
    private BigDecimal montant;
      
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

     @ManyToOne
    @JoinColumn(name = "ID_BON_RETOUR")
    private BonRetour bonRetour;


    public BonRetour getBonRetour() {
        return bonRetour;
    }

    public void setBonRetour(BonRetour bonRetour) {
        this.bonRetour = bonRetour;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public MatierePremier getMatierePremier() {
        return matierePremier;
    }

    public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
    }

 public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getQuantiteRetour() {
        return quantiteRetour;
    }

    public void setQuantiteRetour(BigDecimal quantiteRetour) {
        this.quantiteRetour = quantiteRetour;
    }
    

    
    
  
   

    
}
