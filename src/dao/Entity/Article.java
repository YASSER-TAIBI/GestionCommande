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
@Table(name="ARTICLE")
@NamedQuery(name="Article.findAll", query="SELECT e FROM Article e")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE")
	private String code;

	@Column(name="LIBELLE")
	private String  libelle;
	
        @ManyToOne
        @JoinColumn(name="ID_FOURNISSEUR")
        private Fournisseur fournisseur;
        
        @Column(name="PRIX")
        private BigDecimal prix;
        
        @Column(name="UNITE")
	private String  unite;
        
        @Column(name="TYPE_ARTICLE_DOUANE")
        private String typeArticleDouane;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_TAUX_DEVISE")
        private TauxDevise tauxDevise;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;


	public Article() {
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

        public String getUnite() {
        return unite;
        }

        public void setUnite(String unite) {
        this.unite = unite;
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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getTypeArticleDouane() {
        return typeArticleDouane;
    }

    public void setTypeArticleDouane(String typeArticleDouane) {
        this.typeArticleDouane = typeArticleDouane;
    }

    public TauxDevise getTauxDevise() {
        return tauxDevise;
    }

    public void setTauxDevise(TauxDevise tauxDevise) {
        this.tauxDevise = tauxDevise;
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