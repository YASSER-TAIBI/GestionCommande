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
@Table(name="AVOIR_OULMES")
@NamedQuery(name="AvoirOulmes.findAll", query="SELECT e FROM AvoirOulmes e")
public class AvoirOulmes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        
        @Column(name="ETAT")
	private String etat;
        
        @Column(name="CLIENT")
        private String client ;
        
        @Column(name="FOURNISSEUR")
        private String fournisseur ;
        
        @Column(name="DESIGNATION")
        private String designation ;
        
        @Column(name="NUM_FACTURE")
        private String numFacture ;
        
        @Column(name="FACTURE_AVOIR")
        private BigDecimal factureAvoir ;
        
        @Column(name="FACTURE_SYSTEME")
        private BigDecimal factureSysteme ;
        
        @Column(name="FACTURE_OULMES")
        private BigDecimal factureOulmes ;
        
        @Column(name="AVOIR")
        private BigDecimal avoir ;
        
        @Column(name="ECART")
	private BigDecimal ecart;
        
         @Column(name="REGULARISATION")
	private BigDecimal regularisation;
        
         @Column(name="DATE_FACTURE")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateFacture;
        
        @Column(name="DATE_SAISIE")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateSaisie;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;

        @Column(name="NUM_LIVRAISON")
	private String numLivraison;
        
        @Column(name="CLIENT_2")
	private String client2;
        
        @Column(name="LIEU_LIVRAISON")
	private String lieuLivraison;
        
        @Column(name="TYPE_ECART")
	private String typeEcart;
        
        @OneToMany(cascade = CascadeType.ALL,mappedBy="avoirOulmes")
        private List<DetailAvoirOulmes> detailAvoirOulmes;

        private Boolean action;
        
        
        
        
        
	public AvoirOulmes() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public List<DetailAvoirOulmes> getDetailAvoirOulmes() {
        return detailAvoirOulmes;
    }

    public void setDetailAvoirOulmes(List<DetailAvoirOulmes> detailAvoirOulmes) {
        this.detailAvoirOulmes = detailAvoirOulmes;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public BigDecimal getFactureSysteme() {
        return factureSysteme;
    }

    public void setFactureSysteme(BigDecimal factureSysteme) {
        this.factureSysteme = factureSysteme;
    }

    public BigDecimal getFactureOulmes() {
        return factureOulmes;
    }

    public void setFactureOulmes(BigDecimal factureOulmes) {
        this.factureOulmes = factureOulmes;
    }

    public Boolean getAction() {
        return action;
    }

    public String getTypeEcart() {
        return typeEcart;
    }

    public void setTypeEcart(String typeEcart) {
        this.typeEcart = typeEcart;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

        public BigDecimal getAvoir() {
        return avoir;
        }

        public void setAvoir(BigDecimal avoir) {
        this.avoir = avoir;
        }

        public String getEtat() {
        return etat;
        }

        public void setEtat(String etat) {
        this.etat = etat;
        }

        public String getNumLivraison() {
        return numLivraison;
        }

        public BigDecimal getFactureAvoir() {
        return factureAvoir;
        }

        public void setFactureAvoir(BigDecimal factureAvoir) {
        this.factureAvoir = factureAvoir;
        }

        public String getNumFacture() {
        return numFacture;
        }

        public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
        }

        public BigDecimal getEcart() {
        return ecart;
        }

        public void setEcart(BigDecimal ecart) {
        this.ecart = ecart;
        }

        public String getFournisseur() {
        return fournisseur;
        }

        public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
        }

    public BigDecimal getRegularisation() {
        return regularisation;
    }

    public void setRegularisation(BigDecimal regularisation) {
        this.regularisation = regularisation;
    }

        public void setNumLivraison(String numLivraison) {
        this.numLivraison = numLivraison;
        }

        public Date getDateSaisie() {
        return dateSaisie;
        }

        public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
        }

    public String getClient2() {
        return client2;
    }

    public void setClient2(String client2) {
        this.client2 = client2;
    }

    public String getLieuLivraison() {
        return lieuLivraison;
    }

    public void setLieuLivraison(String lieuLivraison) {
        this.lieuLivraison = lieuLivraison;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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