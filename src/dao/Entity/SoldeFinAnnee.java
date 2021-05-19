package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Transient;


/**
 * The persistent class for the employe database table.
 * 
 */

public class SoldeFinAnnee implements Serializable {


	private int id;

        private Fournisseur fournisseur;

        private BigDecimal ahlBrahim;
        
        private BigDecimal nassTeaPacking;
        
        private BigDecimal saharaPacking;
        
        private BigDecimal elAllaouiFilali;
        
        private BigDecimal greenTea;
 

	public SoldeFinAnnee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public BigDecimal getAhlBrahim() {
        return ahlBrahim;
    }

    public void setAhlBrahim(BigDecimal ahlBrahim) {
        this.ahlBrahim = ahlBrahim;
    }

    public BigDecimal getNassTeaPacking() {
        return nassTeaPacking;
    }

    public void setNassTeaPacking(BigDecimal nassTeaPacking) {
        this.nassTeaPacking = nassTeaPacking;
    }

    public BigDecimal getSaharaPacking() {
        return saharaPacking;
    }

    public void setSaharaPacking(BigDecimal saharaPacking) {
        this.saharaPacking = saharaPacking;
    }

    public BigDecimal getElAllaouiFilali() {
        return elAllaouiFilali;
    }

    public void setElAllaouiFilali(BigDecimal elAllaouiFilali) {
        this.elAllaouiFilali = elAllaouiFilali;
    }

    public BigDecimal getGreenTea() {
        return greenTea;
    }

    public void setGreenTea(BigDecimal greenTea) {
        this.greenTea = greenTea;
    }


    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Transient
    public BigDecimal getMontant() {
        BigDecimal result= (getAhlBrahim().add(getNassTeaPacking()).add(getSaharaPacking()).add(getElAllaouiFilali()).add(getGreenTea())).setScale(2);
         return result;
    }



	
	

}