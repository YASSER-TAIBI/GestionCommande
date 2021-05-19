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
@Table(name="PROMOTION")
@NamedQuery(name="Promotion.findAll", query="SELECT c FROM Promotion c")
public class Promotion implements Serializable {
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
      
    @Column(name="ETAT")
    private String etat;

    @Column(name="NUM_LIVRE_FOUR")
    private String livraisonFour ;
              
    @JoinColumn(name="ID_CLIENT")
    private String client;

    @JoinColumn(name="ID_FOURNISSEUR")
    private String fourisseur  ;
 
    @Column(name="DATE_LIVRAISON")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLivraison;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
   
    @JoinColumn(name = "NUM_COMMANDE")
    private String numCommande;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="promotion")
    private List<DetailPromotion> detailPromotion;
    
    
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public List<DetailPromotion> getDetailPromotion() {
        return detailPromotion;
    }

    public void setDetailPromotion(List<DetailPromotion> detailPromotion) {
        this.detailPromotion = detailPromotion;
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

}
