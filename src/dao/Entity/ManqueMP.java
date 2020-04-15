package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */

public class ManqueMP implements Serializable {

    
	private int id;

	private MatierePremier matierePremier;

	private BigDecimal montant;
        
        private Date dateCreation;
        
        private String numManque;
        
        private String four;
        
        private String etat;
        

        
	public ManqueMP() {
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNumManque() {
        return numManque;
    }

    public void setNumManque(String numManque) {
        this.numManque = numManque;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

   





}