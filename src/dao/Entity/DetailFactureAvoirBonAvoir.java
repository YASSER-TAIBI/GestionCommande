package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * The persistent class for the employe database table.
 * 
 */

public class DetailFactureAvoirBonAvoir implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private BigDecimal  avoirQte;

        private Date dateSaisie;
        
	private BigDecimal  factureAvoirQte;

	private PrixOulmes  prixOulmes;

	private String client;

	private String designation;

	
        
	public DetailFactureAvoirBonAvoir() {
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

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }
    
    

}