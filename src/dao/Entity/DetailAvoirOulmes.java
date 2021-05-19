/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import Utils.Constantes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_AVOIR_OULMES")
@NamedQuery(name = "DetailAvoirOulmes.findAll", query = "SELECT u FROM DetailAvoirOulmes u")
public class DetailAvoirOulmes implements Serializable {
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
      
    @Column(name = "MONTANT_NET")
    private BigDecimal montantNet;
    
    @Column(name = "REMISE_AVOIR")
    private BigDecimal remiseAvoir;
    
    @Column(name = "TYPE_REMISE")
    private String typeRemise;
    
    @Column(name = "QUANTITE_FACTURE_AVOIR")
    private BigDecimal qteFactureAvoir;
    
    @Column(name = "PRIX_FACTURE_AVOIR")
    private BigDecimal prixFactureAvoir;
    
    @Column(name = "REMISE_FACTURE_AVOIR")
    private BigDecimal remiseFactureAvoir;
    
    @Column(name = "MOTIF")
    private String motif;
    
    @Column(name = "NUM_FACTURE")
    private String numFacture;
    
    @Column(name = "MONTANT_NET_FACTURE_AVOIR")
    private BigDecimal montantNetFactureAvoir;
    
    private Boolean action;
          
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @ManyToOne
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;

    @ManyToOne
    @JoinColumn(name = "ID_AVOIR_OULMES")
    private AvoirOulmes avoirOulmes;

     

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

    public AvoirOulmes getAvoirOulmes() {
        return avoirOulmes;
    }

    public void setAvoirOulmes(AvoirOulmes avoirOulmes) {
        this.avoirOulmes = avoirOulmes;
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

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public BigDecimal getRemiseAvoir() {
        return remiseAvoir;
    }

    public void setRemiseAvoir(BigDecimal remiseAvoir) {
        this.remiseAvoir = remiseAvoir;
    }

    public BigDecimal getQteFactureAvoir() {
        return qteFactureAvoir;
    }

    public void setQteFactureAvoir(BigDecimal qteFactureAvoir) {
        this.qteFactureAvoir = qteFactureAvoir;
    }

    public BigDecimal getPrixFactureAvoir() {
        return prixFactureAvoir;
    }

    public void setPrixFactureAvoir(BigDecimal prixFactureAvoir) {
        this.prixFactureAvoir = prixFactureAvoir;
    }

    public BigDecimal getRemiseFactureAvoir() {
        return remiseFactureAvoir;
    }

    public void setRemiseFactureAvoir(BigDecimal remiseFactureAvoir) {
        this.remiseFactureAvoir = remiseFactureAvoir;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public String getTypeRemise() {
        return typeRemise;
    }

    public void setTypeRemise(String typeRemise) {
        this.typeRemise = typeRemise;
    }

    public BigDecimal getMontantNetFactureAvoir() {
        return montantNetFactureAvoir;
    }

    public void setMontantNetFactureAvoir(BigDecimal montantNetFactureAvoir) {
        this.montantNetFactureAvoir = montantNetFactureAvoir;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    
    
        @Transient
    public BigDecimal getEcart() {
        BigDecimal result= getMontantNet().subtract(getMontantNetFactureAvoir()).setScale(2,RoundingMode.FLOOR);

         return result;
    }
 
          @Transient
    public BigDecimal getQteEcart() {
        BigDecimal result= getQte().subtract(getQteFactureAvoir()).setScale(2,RoundingMode.FLOOR);

         return result;
    }
    
}
