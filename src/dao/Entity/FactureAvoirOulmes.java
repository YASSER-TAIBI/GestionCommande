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
@Table(name="FACTURE_AVOIR_OULMES")
@NamedQuery(name="FactureAvoirOulmes.findAll", query="SELECT e FROM FactureAvoirOulmes e")
public class FactureAvoirOulmes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @ManyToOne
        @JoinColumn(name = "ID_PRIX_OULMES")
        private PrixOulmes prixOulmes;
	
        @Column(name="QUANTITE")
	private BigDecimal  qte;
        
        @Column(name="PRIX")
	private BigDecimal  prix;
        
        @Column(name="MONTANT")
	private BigDecimal  montant;
        
        @Column(name="REMISE_AVOIR")
	private BigDecimal  remiseAvoir;
        
        @Column(name="ETAT")
	private String etat;
        
        @Column(name="NUM_FACTURE")
	private String numFacture;  
       
        @Column(name="MONTANT_NET")
	private BigDecimal  montantNet;
        
        private Boolean action;
        
        @Column(name="DATE_SAISIE")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateSaisie;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;

        @Column(name="CLIENT")
        private String client ;
        
        @Column(name="DESIGNATION")
        private String designation ;
        
        @Column(name="NUM_LIVRAISON")
	private String numLivraison;
        
         @Column(name="CLIENT_2")
	private String client2;
        
        @Column(name="LIEU_LIVRAISON")
	private String lieuLivraison;
        
	public FactureAvoirOulmes() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public void setNumLivraison(String numLivraison) {
        this.numLivraison = numLivraison;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getRemiseAvoir() {
        return remiseAvoir;
    }

    public void setRemiseAvoir(BigDecimal remiseAvoir) {
        this.remiseAvoir = remiseAvoir;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
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