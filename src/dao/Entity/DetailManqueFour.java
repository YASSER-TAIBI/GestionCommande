/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
 * @author h
 */
@Entity
@Table(name = "DETAIL_MANQUE_FOUR")
@NamedQuery(name = "DetailManqueFour.findAll", query = "SELECT u FROM DetailManqueFour u")

public class DetailManqueFour implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="N_RETOUR")
    private String numRetour;
    
    @Column(name="N_COMMANDE")
    private String numCommande;
    
    @Column(name="N_BON_LIVRAISION")
    private String numBonLiv;

    @Column(name = "Quantite")
    private BigDecimal quantite ;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @Column(name = "QTE_RECU")
    private BigDecimal quantiteRecu ;

    @Column(name="DATE_SAISIE")
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateSaisie;
    
    @Column(name = "ECART_QTE")
    private BigDecimal ecartQuantite;

    //bi-directional many-to-one association to MatierePremier
    @ManyToOne
    @JoinColumn(name = "id_mat_pre")
    private MatierePremier matierePremier;


    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(BigDecimal quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }

    public DetailManqueFour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumRetour() {
        return numRetour;
    }

    public void setNumRetour(String numRetour) {
        this.numRetour = numRetour;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public BigDecimal getEcartQuantite() {
        return ecartQuantite;
    }

    public void setEcartQuantite(BigDecimal ecartQuantite) {
        this.ecartQuantite = ecartQuantite;
    }
 
    
    public MatierePremier getMatierePremier() {
        return matierePremier;
    }

    public String getNumBonLiv() {
        return numBonLiv;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public void setNumBonLiv(String numBonLiv) {
        this.numBonLiv = numBonLiv;
    }

    public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
    }


    

}
