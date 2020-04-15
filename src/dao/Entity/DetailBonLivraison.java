/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_BON_LIVRAISON")
@NamedQuery(name = "DetailBonLivraison.findAll", query = "SELECT u FROM DetailBonLivraison u")
public class DetailBonLivraison {
      private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Prix")
    private BigDecimal prix;
    
    @Column(name = "Quantite")
    private BigDecimal quantite ;

    @ManyToOne
    @JoinColumn(name = "id_mat_pre")
    private MatierePremier matierePremier;

    @ManyToOne
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;
    
    @Column(name = "REMISE_ACHAT")
    private BigDecimal remiseAchat ;
    
    @Column(name = "Montant")
    private BigDecimal montant;
      
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @JoinColumn(name = "NUM_Commande")
    private String numCommande;
     
    @JoinColumn(name = "CLIENT_2")
        private String client2;
    
    @Column(name="NUM_RECEPTION")
     private String numReception;

    @Column(name="DATE_DETAIL_BON_LIVR")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datedetailBonLivraison;

    @Column(name="NUM_LIVRE_FOUR")
     private String livraisonFour ;

     @Column(name="NUM_FACTURE")
    private String numFacture;
    
    @Column(name="BON_AVOIR")
    private String bonAvoir;
    
    
    
    public String getLivraisonFour() {
        return livraisonFour;
    }

    public void setLivraisonFour(String livraisonFour) {
        this.livraisonFour = livraisonFour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrix() {
        return prix;
    }


    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public MatierePremier getMatierePremier() {
        return matierePremier;
    }

    public String getBonAvoir() {
        return bonAvoir;
    }

    public void setBonAvoir(String bonAvoir) {
        this.bonAvoir = bonAvoir;
    }

    public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getClient2() {
        return client2;
    }

    public void setClient2(String client2) {
        this.client2 = client2;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

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

    public String getNumReception() {
        return numReception;
    }

    public void setNumReception(String numReception) {
        this.numReception = numReception;
    }

    public Date getDatedetailBonLivraison() {
        return datedetailBonLivraison;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public BigDecimal getRemiseAchat() {
        return remiseAchat;
    }

    public void setRemiseAchat(BigDecimal remiseAchat) {
        this.remiseAchat = remiseAchat;
    }

    public void setDatedetailBonLivraison(Date datedetailBonLivraison) {
        this.datedetailBonLivraison = datedetailBonLivraison;
    }


    @Transient
    public BigDecimal getTotal() {
        BigDecimal result =getQuantite().multiply(getPrix()).setScale(2, RoundingMode.HALF_UP);
         return result ;
    }
    
}
