package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */

public class RetourManque implements Serializable {

    
	private int id;

	private MatierePremier matierePremier;

        private PrixOulmes prixOulmes;
        
	private BigDecimal montantRetour;
        
        private BigDecimal montantManque;
        
        private BigDecimal qteManque;
        
        private BigDecimal qteRetour;

        
        
        
    public RetourManque() {
	}

    public int getId() {
		return this.id;
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

    public BigDecimal getMontantRetour() {
        return montantRetour;
    }

    public void setMontantRetour(BigDecimal montantRetour) {
        this.montantRetour = montantRetour;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public BigDecimal getMontantManque() {
        return montantManque;
    }

    public void setMontantManque(BigDecimal montantManque) {
        this.montantManque = montantManque;
    }

    public BigDecimal getQteManque() {
        return qteManque;
    }

    public void setQteManque(BigDecimal qteManque) {
        this.qteManque = qteManque;
    }

    public BigDecimal getQteRetour() {
        return qteRetour;
    }

    public void setQteRetour(BigDecimal qteRetour) {
        this.qteRetour = qteRetour;
    }

  

}