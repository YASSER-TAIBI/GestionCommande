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
@Table(name = "DETAIL_REMISE_FIN_PERIODE")
@NamedQuery(name = "DetailRemiseFinPeriode.findAll", query = "SELECT u FROM DetailRemiseFinPeriode u")
public class DetailRemiseFinPeriode {
      private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "MOIS")
    private int mois;
    
    @Column(name = "CHIFFRE_AFFAIRE")
    private BigDecimal chiffreAffaire;
    
    @Column(name = "CHIFFRE_AFFAIRE_NET")
    private BigDecimal chiffreAffaireNet;
    
    @Column(name = "LIVRAISON_GMS")
    private BigDecimal livraisonGMS;
    
    @Column(name = "REMISE")
    private BigDecimal remise;
    
    @Column(name = "OBJECTIF")
    private BigDecimal objectif ;

    @Column(name = "TRO_CUMULE")
    private BigDecimal troCumule ;
    
    @Column(name = "TAUX_RFP")
    private BigDecimal tauxRfp;
      
    @Column(name = "RFP_REEL")
    private BigDecimal rfpReel;
    
    @Column(name = "RFP")
    private BigDecimal rfp;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @ManyToOne
    @JoinColumn(name = "ID_REMISE_FIN_PERIODE")
    private RemiseFinPeriode remiseFinPeriode;
    
    
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public BigDecimal getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(BigDecimal chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    public BigDecimal getObjectif() {
        return objectif;
    }

    public void setObjectif(BigDecimal objectif) {
        this.objectif = objectif;
    }

    public BigDecimal getTroCumule() {
        return troCumule;
    }

    public void setTroCumule(BigDecimal troCumule) {
        this.troCumule = troCumule;
    }

    public BigDecimal getTauxRfp() {
        return tauxRfp;
    }

    public BigDecimal getChiffreAffaireNet() {
        return chiffreAffaireNet;
    }

    public void setChiffreAffaireNet(BigDecimal chiffreAffaireNet) {
        this.chiffreAffaireNet = chiffreAffaireNet;
    }

    public BigDecimal getLivraisonGMS() {
        return livraisonGMS;
    }

    public void setLivraisonGMS(BigDecimal livraisonGMS) {
        this.livraisonGMS = livraisonGMS;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public BigDecimal getRfpReel() {
        return rfpReel;
    }

    public void setRfpReel(BigDecimal rfpReel) {
        this.rfpReel = rfpReel;
    }

    public void setTauxRfp(BigDecimal tauxRfp) {
        this.tauxRfp = tauxRfp;
    }

    public BigDecimal getRfp() {
        return rfp;
    }

    public void setRfp(BigDecimal rfp) {
        this.rfp = rfp;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public RemiseFinPeriode getRemiseFinPeriode() {
        return remiseFinPeriode;
    }

    public void setRemiseFinPeriode(RemiseFinPeriode remiseFinPeriode) {
        this.remiseFinPeriode = remiseFinPeriode;
    }

    


}
