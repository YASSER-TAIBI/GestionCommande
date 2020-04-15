/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.util.Date;
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

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_DOSSIER")
@NamedQuery(name = "DetailDossier.findAll", query = "SELECT u FROM DetailDossier u")
public class DetailDossier {
     private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    @Column(name="NUM_DOSSIER")
    private String numDossier;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="DATE_CREATION_DOSSIER")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDossier;
    
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMAJ;
    
    @Column(name="ETAT")
    private String etat;
    
    @ManyToOne
    @JoinColumn(name = "ID_COMMANDE_ETRANGERE")
    private CommandeEtrangere commandeEtrangere;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
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

    public CommandeEtrangere getCommandeEtrangere() {
        return commandeEtrangere;
    }

    public void setCommandeEtrangere(CommandeEtrangere commandeEtrangere) {
        this.commandeEtrangere = commandeEtrangere;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Date getDateDossier() {
        return dateDossier;
    }

    public void setDateDossier(Date dateDossier) {
        this.dateDossier = dateDossier;
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
