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
@Table(name="DETAIL_RECEPTION_ETRANGERE")
@NamedQuery(name="DetailReceptionEtrangere.findAll", query="SELECT u FROM DetailReceptionEtrangere u")
public class DetailReceptionEtrangere implements Serializable {
 private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    
    @Column(name="DATE_RECEPTION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateReception;
   
     @Column(name="PRIX_MAD")
     private BigDecimal prix; 
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @Column(name="Quantite_RECU")
    private BigDecimal quantiteRecu ;

    @Column(name="Quantite_LIVREE")
    private BigDecimal quantiteLivree ;
    
    @ManyToOne
    @JoinColumn(name="id_detailCommandeEtrangere")
    private DetailCommandeEtrangere detailCommandeEtrangere;

     @Column(name="IMPORTATEUR")
     private String importateur ;
    
    @Column(name="NBR_CARTON")
    private BigDecimal nbrCarton ;
    
    @Column(name="POIDS_CARTON")
    private BigDecimal poidCarton ;
    
    @Column(name="NUM_RECEPTION")
     private String numReception;
    
     @Column(name="NUM_CONTENEUR")
    private String numConteneur;
    
    @Column(name="NUM_POKING")
    private String numPoking;
    
    @Column(name="NUM_FACTURE")
    private String numFacture;
    
    @Column(name="DATE_FACTURE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFacture;
    

    
    
    public DetailReceptionEtrangere() {
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }


    public String getNumReception() {
        return numReception;
    }

    public void setNumReception(String numReception) {
        this.numReception = numReception;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumConteneur() {
        return numConteneur;
    }

    public void setNumConteneur(String numConteneur) {
        this.numConteneur = numConteneur;
    }

    public String getNumPoking() {
        return numPoking;
    }

    public BigDecimal getQuantiteLivree() {
        return quantiteLivree;
    }

    public void setQuantiteLivree(BigDecimal quantiteLivree) {
        this.quantiteLivree = quantiteLivree;
    }

    public void setNumPoking(String numPoking) {
        this.numPoking = numPoking;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public DetailCommandeEtrangere getDetailCommandeEtrangere() {
        return detailCommandeEtrangere;
    }

    public void setDetailCommandeEtrangere(DetailCommandeEtrangere detailCommandeEtrangere) {
        this.detailCommandeEtrangere = detailCommandeEtrangere;
    }

    public String getImportateur() {
        return importateur;
    }

    public void setImportateur(String importateur) {
        this.importateur = importateur;
    }

    public BigDecimal getNbrCarton() {
        return nbrCarton;
    }

    public void setNbrCarton(BigDecimal nbrCarton) {
        this.nbrCarton = nbrCarton;
    }

    public BigDecimal getPoidCarton() {
        return poidCarton;
    }

    public void setPoidCarton(BigDecimal poidCarton) {
        this.poidCarton = poidCarton;
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
