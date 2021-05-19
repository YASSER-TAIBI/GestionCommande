package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="CHIFFRE_AFFAIRE_BRUT")
@NamedQuery(name="ChiffreAffaireBrut.findAll", query="SELECT e FROM ChiffreAffaireBrut e")
public class ChiffreAffaireBrut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="NOM_MOIS")
	private String nomMois;

        @Column(name="NUM_MOIS")
        private int numMois;
        
        @Column(name="EAU")
        private BigDecimal eau;
        
        @Column(name="SODA")
        private BigDecimal soda;
        
        @Column(name="TOTAL")
        private BigDecimal total;
        
       
        
	public ChiffreAffaireBrut() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getNomMois() {
        return nomMois;
    }

    public void setNomMois(String nomMois) {
        this.nomMois = nomMois;
    }

    public int getNumMois() {
        return numMois;
    }

    public void setNumMois(int numMois) {
        this.numMois = numMois;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

 
        
        
}