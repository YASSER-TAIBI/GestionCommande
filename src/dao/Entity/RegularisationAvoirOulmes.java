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
@Table(name = "REGULARISATION_AVOIR_OULMES")
@NamedQuery(name = "RegularisationAvoirOulmes.findAll", query = "SELECT u FROM RegularisationAvoirOulmes u")
public class RegularisationAvoirOulmes implements Serializable {
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

    @Column(name="NUM_FACTURE")
    private String numFacture;
    
    @Column(name="QTE")
    private BigDecimal qte;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
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
