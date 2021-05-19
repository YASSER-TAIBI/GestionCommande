package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="BAREME_TAUX")
@NamedQuery(name="BaremeTaux.findAll", query="SELECT e FROM BaremeTaux e")
public class BaremeTaux implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="TAUX_REALISATION_OBJECTIF_MIN")
	private BigDecimal tauxRealisationObjectifMin;

        @Column(name="TAUX_REALISATION_OBJECTIF_MAX")
	private BigDecimal tauxRealisationObjectifMax;
        
        @Column(name="REMISE_FIN_PERIODE")
        private BigDecimal remiseFinPeriode ;
        
        @Column(name="EAU")
        private BigDecimal eau ;
        
        @Column(name="SODA")
        private BigDecimal soda ;
        
 
	public BaremeTaux() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public BigDecimal getTauxRealisationObjectifMin() {
        return tauxRealisationObjectifMin;
    }

    public void setTauxRealisationObjectifMin(BigDecimal tauxRealisationObjectifMin) {
        this.tauxRealisationObjectifMin = tauxRealisationObjectifMin;
    }

    public BigDecimal getTauxRealisationObjectifMax() {
        return tauxRealisationObjectifMax;
    }

    public void setTauxRealisationObjectifMax(BigDecimal tauxRealisationObjectifMax) {
        this.tauxRealisationObjectifMax = tauxRealisationObjectifMax;
    }

    public BigDecimal getRemiseFinPeriode() {
        return remiseFinPeriode;
    }

    public void setRemiseFinPeriode(BigDecimal remiseFinPeriode) {
        this.remiseFinPeriode = remiseFinPeriode;
    }

    public BigDecimal getEau() {
        return eau;
    }

    public void setEau(BigDecimal eau) {
        this.eau = eau;
    }

    public BigDecimal getSoda() {
        return soda;
    }

    public void setSoda(BigDecimal soda) {
        this.soda = soda;
    }

  


	
	

}