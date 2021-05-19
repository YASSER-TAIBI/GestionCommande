/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author h
 */
@Entity
@Table(name="DETAIL_RECEPTION_REGION")
@NamedQuery(name="DetailReceptionRegion.findAll", query="SELECT u FROM DetailReceptionRegion u")
public class DetailReceptionRegion implements Serializable {
 private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
   
     @Column(name="PRIX")
     private BigDecimal prix; 
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @Column(name="Quantite_RECU")
    private BigDecimal quantiteRecu ;

    @ManyToOne
    @JoinColumn(name="id_detailCommandeRegion")
    private DetailCommandeRegion detailCommandeRegion;
    
    @Column(name="NUM_RECEPTION_REGION")
     private String numReceptionRegion;

    @Column(name="NUM_COMMANDE")
     private String numCommande;
    
     @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private ClientMP clientMP;
        
    @ManyToOne
    @JoinColumn(name="ID_FOURNISSEUR")
    private Fournisseur fourisseur;

    
    
    public DetailReceptionRegion() {
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public ClientMP getClientMP() {
        return clientMP;
    }

    public void setClientMP(ClientMP clientMP) {
        this.clientMP = clientMP;
    }

    public Fournisseur getFourisseur() {
        return fourisseur;
    }

    public void setFourisseur(Fournisseur fourisseur) {
        this.fourisseur = fourisseur;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNumReceptionRegion() {
        return numReceptionRegion;
    }

    public void setNumReceptionRegion(String numReceptionRegion) {
        this.numReceptionRegion = numReceptionRegion;
    }

    public int getId() {
        return id;
    }

    public DetailCommandeRegion getDetailCommandeRegion() {
        return detailCommandeRegion;
    }

    public void setDetailCommandeRegion(DetailCommandeRegion detailCommandeRegion) {
        this.detailCommandeRegion = detailCommandeRegion;
    }

    public void setId(int id) {
        this.id = id;
    }

     public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(BigDecimal quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }


   

   
    
}
