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
@Table(name = "PRIX_OULMES")
@NamedQuery(name="PrixOulmes.findAll", query="SELECT u FROM PrixOulmes u")
public class PrixOulmes  implements Serializable{
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="FOURNISSEUR")
    private String fournisseur ;
    
    @ManyToOne
    @JoinColumn(name="ID_PRODUIT")
    private Produit produit;
      
    @Column(name="PRIX")
    private BigDecimal prix ;
      
     @Column(name="REMISE_AVOIR")
    private BigDecimal RemiseAvoir ;
    
     @Column(name="REMISE_ACHAT")
    private BigDecimal RemiseAchat ;
    
      @Column(name="CONDITIONNEMENT")
    private BigDecimal Conditionnement ;
      
       @Column(name="CONDITIONNEMENT_CAISSE")
    private BigDecimal ConditionnementCaisse ;
       
     @Column(name="TYPE_ARTICLE")
    private String TypeArticle ;  
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @Column(name="CLIENT")
    private String client ;  
         
    @Column(name="LIEU_LIVRAISON")
    private String lieuLivraison ;  
         
         
         
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public BigDecimal getRemiseAvoir() {
        return RemiseAvoir;
    }

    public void setRemiseAvoir(BigDecimal RemiseAvoir) {
        this.RemiseAvoir = RemiseAvoir;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public BigDecimal getRemiseAchat() {
        return RemiseAchat;
    }

    public void setRemiseAchat(BigDecimal RemiseAchat) {
        this.RemiseAchat = RemiseAchat;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getConditionnement() {
        return Conditionnement;
    }

    public String getLieuLivraison() {
        return lieuLivraison;
    }

    public void setLieuLivraison(String lieuLivraison) {
        this.lieuLivraison = lieuLivraison;
    }

    public void setConditionnement(BigDecimal Conditionnement) {
        this.Conditionnement = Conditionnement;
    }

    public BigDecimal getConditionnementCaisse() {
        return ConditionnementCaisse;
    }

    public void setConditionnementCaisse(BigDecimal ConditionnementCaisse) {
        this.ConditionnementCaisse = ConditionnementCaisse;
    }

    public String getTypeArticle() {
        return TypeArticle;
    }

    public void setTypeArticle(String TypeArticle) {
        this.TypeArticle = TypeArticle;
    }
         
}
