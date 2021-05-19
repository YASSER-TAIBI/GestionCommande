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
@Table(name="REMISE_ARTICLE_OULMES")
@NamedQuery(name="RemiseArticleOulmes.findAll", query="SELECT c FROM RemiseArticleOulmes c")
public class RemiseArticleOulmes implements Serializable {
	private static final long serialVersionUID = 1L;

        
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="ANNEE")
        private String annee;

        @Column(name = "MOIS")
        private int mois;
        
        @Column(name = "TRO_CUMULE")
        private BigDecimal troCumule ;
    
        @Column(name = "TAUX_RFP")
        private BigDecimal tauxRfp;
        
        @Column(name = "REMISE")
        private BigDecimal Remise;
        
        @ManyToOne
        @JoinColumn(name = "ID_PRIX_OULMES")
        private PrixOulmes prixOulmes;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
     
        
        
	public RemiseArticleOulmes() {
	}

        public int getId() {
        return id;
        }

        public void setId(int id) {
        this.id = id;
        }

        public String getAnnee() {
        return annee;
        }

        public void setAnnee(String annee) {
        this.annee = annee;
        }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
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

    public BigDecimal getRemise() {
        return Remise;
    }

    public void setRemise(BigDecimal Remise) {
        this.Remise = Remise;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

}