/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import Utils.Constantes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name="BON_LIVRAISON")
@NamedQuery(name="BonLivraison.findAll", query="SELECT c FROM BonLivraison c")
public class BonLivraison implements Serializable {
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
   

    
    @Column(name="DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
     
    @Column(name="NUM_RECEPTION")
     private String numReception;
      
    @Column(name="ETAT")
    private String etat;
          
    @Column(name="MONTANT")
    private BigDecimal montant;
    
    @Column(name="MONTANT_TVA")
    private BigDecimal montantTVA;
    
    @Column(name="MONTANT_TTC")
    private BigDecimal montantTTC;
    
    @Column(name="NUM_LIVRE_FOUR")
    private String livraisonFour ;
              
    @JoinColumn(name="ID_CLIENT")
    private String client;

    @JoinColumn(name="ID_FOURNISSEUR")
    private String fourisseur  ;
    
    @Column(name="TYPE_BON")
    private String typeBon  ;
         
    @Column(name="DATE_LIVRAISON")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLivraison;
   
    @Column(name="DATE_PAIEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePaiement;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @JoinColumn(name = "NOMBRE_JOUR")
    private int nombreJour;
   
    @JoinColumn(name = "id_commande")
    private String numCommande;

    @Column(name="NUM_FACTURE")
    private String numFacture;
 
    private Boolean action;

    private Boolean sansTVA;
    
    
    
    
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public int getNombreJour() {
        return nombreJour;
    }

    public void setNombreJour(int nombreJour) {
        this.nombreJour = nombreJour;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeBon() {
        return typeBon;
    }

    public void setTypeBon(String typeBon) {
        this.typeBon = typeBon;
    }

    public Boolean getSansTVA() {
        return sansTVA;
    }

    public void setSansTVA(Boolean sansTVA) {
        this.sansTVA = sansTVA;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumReception() {
        return numReception;
    }

    public void setNumReception(String numReception) {
        this.numReception = numReception;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getLivraisonFour() {
        return livraisonFour;
    }

    public void setLivraisonFour(String livraisonFour) {
        this.livraisonFour = livraisonFour;
    }
    
    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFourisseur() {
        return fourisseur;
    }

    public void setFournisseur(String fourisseur) {
        this.fourisseur = fourisseur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(BigDecimal montantTVA) {
        this.montantTVA = montantTVA;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    
}
