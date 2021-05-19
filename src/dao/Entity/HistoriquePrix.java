/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HISTORIQUE_PRIX")
@NamedQuery(name="HistoriquePrix.findAll", query="SELECT u FROM HistoriquePrix u")
public class HistoriquePrix  implements Serializable{
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
      @ManyToOne
      @JoinColumn(name="ID_FOURNISSEUR")
      private Fournisseur fournisseur;
      
      @Column(name="ANCIEN_PRIX")
      private BigDecimal ancienPrix ;

      @Column(name="NOUVEAU_PRIX")
      private BigDecimal nouveauPrix ;
      
      @Column(name="CHEMIN")
      private String chemin ;
      
      @Column(name="DATE_CREATION")
      @Temporal(javax.persistence.TemporalType.DATE)
      private Date dateCreation;
      
      @ManyToOne
      @JoinColumn(name="ID_UTIL_CREATION")
      private Utilisateur utilisateurCreation;

      @ManyToOne
      @JoinColumn(name="ID_CATEGORIE_MP")
      private CategorieMp categorieMp ;
      
      @ManyToOne
      @JoinColumn(name = "ID_PRIX_OULMES")
      private PrixOulmes prixOulmes;
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public BigDecimal getAncienPrix() {
        return ancienPrix;
    }

    public void setAncienPrix(BigDecimal ancienPrix) {
        this.ancienPrix = ancienPrix;
    }

    public BigDecimal getNouveauPrix() {
        return nouveauPrix;
    }

    public void setNouveauPrix(BigDecimal nouveauPrix) {
        this.nouveauPrix = nouveauPrix;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public CategorieMp getCategorieMp() {
        return categorieMp;
    }

    public void setCategorieMp(CategorieMp categorieMp) {
        this.categorieMp = categorieMp;
    }

    
         
         
         
 

}
