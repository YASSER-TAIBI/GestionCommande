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
@Table(name="DETAIL_RECEPTION")
@NamedQuery(name="DetailReception.findAll", query="SELECT u FROM DetailReception u")
public class DetailReception implements Serializable {
 private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    
    @Column(name="DATE_RECEPTION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateReception;
   
     @Column(name="PRIX")
     private BigDecimal prix; 
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

   
    @Column(name="Quantite_RECU")
    private BigDecimal quantiteRecu ;
     

    @ManyToOne
    @JoinColumn(name="id_detailCommande")
    private DetailCommande detailCommande;

     @Column(name="NUM_LIVRE_FOUR")
     private String livraisonFour ;
     
    @Column(name="DATE_LIVRE_FOUR")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date livrFour;
    
    @Column(name="NUM_RECEPTION")
     private String numReception;

 

    public DetailReception() {
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getLivraisonFour() {
        return livraisonFour;
    }

    public void setLivraisonFour(String livraisonFour) {
        this.livraisonFour = livraisonFour;
    }



    public Date getLivrFour() {
        return livrFour;
    }

    public void setLivrFour(Date livrFour) {
        this.livrFour = livrFour;
    }

    public String getNumReception() {
        return numReception;
    }

    public void setNumReception(String numReception) {
        this.numReception = numReception;
    }
     
     
    public DetailCommande getDetailCommande() {
        return detailCommande;
    }

    public void setDetailCommande(DetailCommande detailCommande) {
        this.detailCommande = detailCommande;
    }

     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
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
