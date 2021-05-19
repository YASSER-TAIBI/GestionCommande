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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author h
 */
@Entity
@Table(name = "COMMANDE_REGION")
@NamedQuery(name="Commande_Region.findAll", query="SELECT u FROM CommandeRegion u")
public class CommandeRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Object nCommandeColumn;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="N_COMMANDE")
    private String nCommande;
    
  
    @Column(name="DATE_SAISIE")
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateSaisie;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMAJ;
    
    @Column(name="ETAT")
    private String etat;
    
    @Column(name="TYPE_COMMANDE")
    private String typeCommande;
  
    @ManyToOne
    @JoinColumn(name="ID_DEPOT")
    private Depot depot;

    @Column(name="N_MATRICULE")
    private String nMatricule;
    
    @Column(name="DATE_CHARGEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateChargement;
    
    //bi-directional many-to-one association to DetailCommande  fetch = FetchType.EAGER , orphanRemoval = true
    @OneToMany(mappedBy="commandeRegion",cascade = {CascadeType.ALL})
    private List<DetailCommandeRegion> detailCommandeRegion= new ArrayList<DetailCommandeRegion>();
        
          //bi-directional many-to-one association to MatierePremier


    @Column(name="PRIX_TOTAL")
    private BigDecimal prixTotal ;

    
    
    
    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public Depot getDepot() {
        return depot;
    }

    public String getnMatricule() {
        return nMatricule;
    }

    public void setnMatricule(String nMatricule) {
        this.nMatricule = nMatricule;
    }

    public Date getDateChargement() {
        return dateChargement;
    }

    public void setDateChargement(Date dateChargement) {
        this.dateChargement = dateChargement;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public String getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public List<DetailCommandeRegion> getDetailCommandeRegion() {
        return detailCommandeRegion;
    }

    public void setDetailCommandeRegion(List<DetailCommandeRegion> detailCommandeRegion) {
        this.detailCommandeRegion = detailCommandeRegion;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
    
    public CommandeRegion(){}

    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnCommande() {
        return nCommande;
    }

    public void setnCommande(String nCommande) {
        this.nCommande = nCommande;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public static Object getnCommandeColumn() {
        return nCommandeColumn;
    }

    public static void setnCommandeColumn(Object nCommandeColumn) {
        CommandeRegion.nCommandeColumn = nCommandeColumn;
    }

}
