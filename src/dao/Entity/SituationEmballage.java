package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Transient;


/**
 * The persistent class for the employe database table.
 * 
 */

public class SituationEmballage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private BigDecimal  initial;

	private BigDecimal  achat;

	private PrixOulmes prixOulmes;
       
        private BigDecimal  prix;
       
	private BigDecimal  avoir;

        private String  client;
        
        private String  fournisser;
        
        private BigDecimal  sFinal;
        
        private BigDecimal  montant;
        
        
        
        
        
	public SituationEmballage() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getInitial() {
        return initial;
    }

    public void setInitial(BigDecimal initial) {
        this.initial = initial;
    }

    public BigDecimal getAchat() {
        return achat;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public void setAchat(BigDecimal achat) {
        this.achat = achat;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public BigDecimal getAvoir() {
        return avoir;
    }

    public void setAvoir(BigDecimal avoir) {
        this.avoir = avoir;
    }

    public String getFournisser() {
        return fournisser;
    }

    public void setFournisser(String fournisser) {
        this.fournisser = fournisser;
    }

    public BigDecimal getsFinal() {
        return sFinal;
    }

    public void setsFinal(BigDecimal sFinal) {
        this.sFinal = sFinal;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
    
    public void setClient(String client) {
        this.client = client;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClient() {
        return client;
    }

    
        @Transient
    public BigDecimal getCalculeSfinal() {
        BigDecimal result= (getInitial().add(getAchat())).subtract(getAvoir()).setScale(2, RoundingMode.FLOOR);
         return result;
    }
    
        @Transient
    public BigDecimal getCalculeMontant() {
        BigDecimal result= getCalculeSfinal().multiply(getPrix()).setScale(2, RoundingMode.FLOOR);
         return result ;
  
    }
    
}