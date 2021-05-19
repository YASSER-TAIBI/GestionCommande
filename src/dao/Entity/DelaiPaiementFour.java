/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DELAI_PAIEMENT_FOUR")
@NamedQuery(name="DelaiPaiementFour.findAll", query="SELECT u FROM DelaiPaiementFour u")
public class DelaiPaiementFour  implements Serializable{
    
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

     @ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Fournisseur fournisseur;
     
      @Column(name="NB_JOUR_MIN")
        private BigDecimal nbJourMin;
      
      @Column(name="NB_JOUR")
        private BigDecimal nbJour;

      @Column(name="COMMANDE_SP")
        private BigDecimal commandeSP;
      
      @Column(name="NB_JOUR_SP")
        private BigDecimal nbJourSP;
      
         @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

         @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
         
    @Column(name="ETAT")
    private String etat;
         
         
         
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getNbJourMin() {
        return nbJourMin;
    }

    public void setNbJourMin(BigDecimal nbJourMin) {
        this.nbJourMin = nbJourMin;
    }

    public BigDecimal getNbJour() {
        return nbJour;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNbJour(BigDecimal nbJour) {
        this.nbJour = nbJour;
    }

    public BigDecimal getCommandeSP() {
        return commandeSP;
    }

    public void setCommandeSP(BigDecimal commandeSP) {
        this.commandeSP = commandeSP;
    }

    public BigDecimal getNbJourSP() {
        return nbJourSP;
    }

    public void setNbJourSP(BigDecimal nbJourSP) {
        this.nbJourSP = nbJourSP;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

  
         
}
