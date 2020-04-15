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
@Table(name="COMMANDE_ETRANGERE")
@NamedQuery(name="CommandeEtrangere.findAll", query="SELECT u FROM CommandeEtrangere u")
public class CommandeEtrangere implements Serializable {
    
    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="NUM_COMMANDE")
    private String nCommande;
    
    @Column(name="DATE_COMMANDE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCommande;
    
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
    
    @ManyToOne
    @JoinColumn(name="ID_FOURNISSEUR")
    private Fournisseur fourisseur;
    
    @Column(name="LIEU_DEPART")
    private String lieuDepart;

    @Column(name="LIEU_ARRIVEE")
    private String lieuArrivee;
    
    @Column(name="DATE_DEPART")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDepart;

    @Column(name="DATE_EN_PORT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEnPort;
    
    @Column(name="DATE_ARRIVEE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateArrivee;
    

//    @Column(name="NUM_DOSSIER")
//    private String numDoussier;
//    
//    @Column(name="DATE_DOSSIER")
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date dateDoussier;
    
    @Column(name="DATE_CHARGEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateChargement;
    
    @Column(name="ACTION")
    private boolean action ;
    
    @OneToMany(mappedBy="commandeEtrangere",cascade = {CascadeType.ALL})
    private List<DetailCommandeEtrangere> detailCommandeEtrangere= new ArrayList<DetailCommandeEtrangere>();
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Date getDateChargement() {
        return dateChargement;
    }

    public Date getDateEnPort() {
        return dateEnPort;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

//    public String getNumDoussier() {
//        return numDoussier;
//    }
//
//    public void setNumDoussier(String numDoussier) {
//        this.numDoussier = numDoussier;
//    }
//
//    public Date getDateDoussier() {
//        return dateDoussier;
//    }
//
//    public void setDateDoussier(Date dateDoussier) {
//        this.dateDoussier = dateDoussier;
//    }

    public void setDateEnPort(Date dateEnPort) {
        this.dateEnPort = dateEnPort;
    }

    public void setDateChargement(Date dateChargement) {
        this.dateChargement = dateChargement;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
  
       public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

  
   public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public List<DetailCommandeEtrangere> getDetailCommandeEtrangere() {
        return detailCommandeEtrangere;
    }

    public void setDetailCommandeEtrangere(List<DetailCommandeEtrangere> detailCommandeEtrangere) {
        this.detailCommandeEtrangere = detailCommandeEtrangere;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Fournisseur getFourisseur() {
        return fourisseur;
    }

    public void setFourisseur(Fournisseur fourisseur) {
        this.fourisseur = fourisseur;
    }

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

  
}
