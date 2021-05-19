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
@Table(name = "DETAIL_PROMOTION")
@NamedQuery(name = "DetailPromotion.findAll", query = "SELECT u FROM DetailPromotion u")
public class DetailPromotion {
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
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;
    
    @Column(name = "REMISE_DEFAUT")
    private BigDecimal remiseDefaut ;
    
    @Column(name = "Montant")
    private BigDecimal montant;
      
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOTION")
    private Promotion promotion;
    
    
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getRemiseDefaut() {
        return remiseDefaut;
    }

    public void setRemiseDefaut(BigDecimal remiseDefaut) {
        this.remiseDefaut = remiseDefaut;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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

    public BigDecimal getMontant() {
        return montant;
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

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }
    
}
