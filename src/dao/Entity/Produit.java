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
@Entity
@Table(name="PRODUIT")
@NamedQuery(name="Produit.findAll", query="SELECT e FROM Produit e")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE")
	private String code;

	@Column(name="LIBELLE")
	private String  libelle;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @Column(name="QTE_PALETTE")
        private BigDecimal qtePalette ;
        
        @Column(name="QTE_CAISSE")
        private BigDecimal qteCaisse ;
        
        @Column(name="QTE_BOUTEILLE")
        private BigDecimal qteBouteille  ;
        
        @Column(name="PALETTE")
	private Boolean  palette ;

        @Column(name="ETAT")
        private String etat;
        
        
        
	public Produit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

    public BigDecimal getQtePalette() {
        return qtePalette;
    }

    public void setQtePalette(BigDecimal qtePalette) {
        this.qtePalette = qtePalette;
    }

        public Boolean getPalette() {
        return palette;
        }

        public void setPalette(Boolean palette) {
        this.palette = palette;
        }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public BigDecimal getQteCaisse() {
        return qteCaisse;
    }

    public void setQteCaisse(BigDecimal qteCaisse) {
        this.qteCaisse = qteCaisse;
    }

    public BigDecimal getQteBouteille() {
        return qteBouteille;
    }

    public void setQteBouteille(BigDecimal qteBouteille) {
        this.qteBouteille = qteBouteille;
    }


	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

        public Date getDateCreation() {
        return dateCreation;
        }

        public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
        }

        public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
        }

        public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
        }


	
	

}