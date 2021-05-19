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
import javax.persistence.Table;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_PROMOTION_ACCORDEE")
@NamedQuery(name = "DetailPromotionAccordee.findAll", query = "SELECT u FROM DetailPromotionAccordee u")
public class DetailPromotionAccordee implements Serializable {
     private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "PRIX")
    private BigDecimal prix;
    
    @Column(name = "QUANTITE")
    private BigDecimal qte;

    @Column(name = "MONTANT")
    private BigDecimal montant;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @ManyToOne
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOTION_ACCORDEE")
    private PromotionAccordee promotionAccordee;

     

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

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public PromotionAccordee getPromotionAccordee() {
        return promotionAccordee;
    }

    public void setPromotionAccordee(PromotionAccordee promotionAccordee) {
        this.promotionAccordee = promotionAccordee;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

}
