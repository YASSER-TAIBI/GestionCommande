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
 * The persistent class for the employe database table.
 * 
 */

public class SituationGlobalAvoir implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private BigDecimal  avoirQte;

	private BigDecimal  factureAvoirQte;

	private PrixOulmes  prixOulmes;

	private BigDecimal  qteRestee;

        private String  client;
        
	private BigDecimal  Prix;

	private BigDecimal  Montant;

	private BigDecimal  MontantTVA;
        
        private BigDecimal  MontantTTC;
        
	public SituationGlobalAvoir() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAvoirQte() {
        return avoirQte;
    }

    public void setAvoirQte(BigDecimal avoirQte) {
        this.avoirQte = avoirQte;
    }

    public BigDecimal getFactureAvoirQte() {
        return factureAvoirQte;
    }

    public void setFactureAvoirQte(BigDecimal factureAvoirQte) {
        this.factureAvoirQte = factureAvoirQte;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public BigDecimal getQteRestee() {
        return qteRestee;
    }

    public void setQteRestee(BigDecimal qteRestee) {
        this.qteRestee = qteRestee;
    }

    public BigDecimal getPrix() {
        return Prix;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClient() {
        return client;
    }

    public void setPrix(BigDecimal Prix) {
        this.Prix = Prix;
    }

    public BigDecimal getMontant() {
        return Montant;
    }

    public BigDecimal getMontantTVA() {
        return MontantTVA;
    }

    public void setMontantTVA(BigDecimal MontantTVA) {
        this.MontantTVA = MontantTVA;
    }

    public BigDecimal getMontantTTC() {
        return MontantTTC;
    }

    public void setMontantTTC(BigDecimal MontantTTC) {
        this.MontantTTC = MontantTTC;
    }

    public void setMontant(BigDecimal Montant) {
        this.Montant = Montant;
    }


}