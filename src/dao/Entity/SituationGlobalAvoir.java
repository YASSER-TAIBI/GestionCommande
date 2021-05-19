package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the employe database table.
 * 
 */

public class SituationGlobalAvoir implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private BigDecimal  avoirQte;

	private BigDecimal  factureAvoirQte;

	private Produit produit;
        
	private BigDecimal  qteRestee;

        private String  client;
        
        
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

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public BigDecimal getQteRestee() {
        return qteRestee;
    }

    public void setQteRestee(BigDecimal qteRestee) {
        this.qteRestee = qteRestee;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClient() {
        return client;
    }

}