/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author h
 */
@Entity
@NamedQuery(name="AvanceFournisseur.findAll", query="SELECT u FROM AvanceFournisseur u")
public class AvanceFournisseur implements Serializable {
    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="CODE")
    private String code;
    
    @Column(name="MODE_AVANCE")
    private String modeAvance;
    
    @Column(name="NUM_AVANCE")
    private String numAvance;
    
    @Column(name="DATE_AVANCE_FOUR")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAvanceFour;
    
    @Column(name="DATE_ECHEANCE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEcheance;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMAJ;
    
    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private ClientMP clientMP;
        
    @ManyToOne
    @JoinColumn(name="ID_FOURNISSEUR")
    private Fournisseur fourisseur;

    @Column(name="REFERENCE")
    private String reference;
    
    @Column(name="MONTANT")
    private BigDecimal montant ;

    @Column(name="ETAT")
    private String etat;
    
    @Column(name="ACTION")
    private Boolean action;
    
  


    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public ClientMP getClientMP() {
        return clientMP;
    }

    public Date getDateAvanceFour() {
        return dateAvanceFour;
    }

    public String getModeAvance() {
        return modeAvance;
    }

    public void setModeAvance(String modeAvance) {
        this.modeAvance = modeAvance;
    }

    public String getNumAvance() {
        return numAvance;
    }

    public void setNumAvance(String numAvance) {
        this.numAvance = numAvance;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public void setDateAvanceFour(Date dateAvanceFour) {
        this.dateAvanceFour = dateAvanceFour;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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
    
    public AvanceFournisseur(){}

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }






   
    
}
