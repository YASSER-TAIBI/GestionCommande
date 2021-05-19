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
@Table(name = "HISTORIQUE_AVOIR_OULMES")
@NamedQuery(name = "HistoriqueAvoirOulmes.findAll", query = "SELECT u FROM HistoriqueAvoirOulmes u")
public class HistoriqueAvoirOulmes implements Serializable {
     private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    
     @Column(name = "BON_AVOIR")
    private String bonAvoir;
    
    @Column(name="DATE_SAISIE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSaisie;

    @Column(name="ID_CLIENT")
    private String client;
      
    @Column(name="ID_CLIENT_2")
    private String client2;
    
      @Column(name="ANCIEN_PRIX")
      private BigDecimal ancienPrix ;

      @Column(name="NOUVEAU_PRIX")
      private BigDecimal nouveauPrix ;
      

      @ManyToOne
      @JoinColumn(name="ID_UTIL_CREATION")
      private Utilisateur utilisateurCreation;

      
      @ManyToOne
      @JoinColumn(name = "ID_PRIX_OULMES")
      private PrixOulmes prixOulmes;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient2() {
        return client2;
    }

    public void setClient2(String client2) {
        this.client2 = client2;
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

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public String getBonAvoir() {
        return bonAvoir;
    }

    public void setBonAvoir(String bonAvoir) {
        this.bonAvoir = bonAvoir;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }
    
    

   
 


}
