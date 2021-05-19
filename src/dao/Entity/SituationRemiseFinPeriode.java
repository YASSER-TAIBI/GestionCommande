package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the employe database table.
 * 
 */

public class SituationRemiseFinPeriode implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int mois;

	private BigDecimal chiffreAffaire;

	private BigDecimal objectif ;
        
        private BigDecimal  troCumule;

	private BigDecimal  tauxRfp;

        private BigDecimal  rfp;
        
        private BigDecimal  livGms;
        
        private BigDecimal  remise;
        
        private BigDecimal  rfpReel;
        
        private BigDecimal  caNet;
        
        
        
    public SituationRemiseFinPeriode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public BigDecimal getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(BigDecimal chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    public BigDecimal getObjectif() {
        return objectif;
    }

    public void setObjectif(BigDecimal objectif) {
        this.objectif = objectif;
    }

    public BigDecimal getTroCumule() {
        return troCumule;
    }

    public void setTroCumule(BigDecimal troCumule) {
        this.troCumule = troCumule;
    }

    public BigDecimal getTauxRfp() {
        return tauxRfp;
    }

    public void setTauxRfp(BigDecimal tauxRfp) {
        this.tauxRfp = tauxRfp;
    }

    public BigDecimal getRfp() {
        return rfp;
    }

    public void setRfp(BigDecimal rfp) {
        this.rfp = rfp;
    }

    public BigDecimal getLivGms() {
        return livGms;
    }

    public void setLivGms(BigDecimal livGms) {
        this.livGms = livGms;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public BigDecimal getRfpReel() {
        return rfpReel;
    }

    public void setRfpReel(BigDecimal rfpReel) {
        this.rfpReel = rfpReel;
    }

    public BigDecimal getCaNet() {
        return caNet;
    }

    public void setCaNet(BigDecimal caNet) {
        this.caNet = caNet;
    }

 

}