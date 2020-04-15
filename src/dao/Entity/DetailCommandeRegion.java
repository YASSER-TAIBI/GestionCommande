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
import javax.persistence.Transient;


/**
 *
 * @author h
 */
@Entity
@Table(name = "DETAIL_COMMANDE_REGION")
@NamedQuery(name = "DetailCommandeRegion.findAll", query = "SELECT u FROM DetailCommandeRegion u")

public class DetailCommandeRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    public static DetailCommandeRegion get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    private BigDecimal prixUnitaire;

    @Column(name = "Quantite")
    private BigDecimal quantite ;
    
    @Column(name = "QuantiteRecu")
    private BigDecimal quantiteRecu ;
    
    @Column(name = "QTE_RESTEE")
    private BigDecimal quantiteRestee;

    @Column(name = "QTE_LIVREE")
    private BigDecimal quantiteLivree;
    
    @Column(name="ETAT")
    private String etat;
    
    @Column(name="CMD_REGLE")
    private String cmdRegle;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    //bi-directional many-to-one association to MatierePremier
   
    @ManyToOne
    @JoinColumn(name = "id_mat_pre")
    private MatierePremier matierePremier;

    //bi-directional many-to-one association to Commande cascade = {CascadeType.ALL}
 
    @ManyToOne
    @JoinColumn(name = "id_commandeRegion")
    private CommandeRegion commandeRegion;

//    (cascade = {CascadeType.ALL})
    @ManyToOne
    @JoinColumn(name = "DIMENSION")
    private Dimension dimension = new Dimension();

      
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


    
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public DetailCommandeRegion() {
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public MatierePremier getMatierePremier() {
        return matierePremier;
    }

    public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public BigDecimal getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(BigDecimal quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }

    public CommandeRegion getCommandeRegion() {
        return commandeRegion;
    }

    public void setCommandeRegion(CommandeRegion commandeRegion) {
        this.commandeRegion = commandeRegion;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public String getCmdRegle() {
        return cmdRegle;
    }

    public void setCmdRegle(String cmdRegle) {
        this.cmdRegle = cmdRegle;
    }

    public BigDecimal getQuantiteRestee() {
        return quantiteRestee;
    }

    public void setQuantiteRestee(BigDecimal quantiteRestee) {
        this.quantiteRestee = quantiteRestee;
    }

    public BigDecimal getQuantiteLivree() {
        return quantiteLivree;
    }

    public void setQuantiteLivree(BigDecimal quantiteLivree) {
        this.quantiteLivree = quantiteLivree;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    

}
