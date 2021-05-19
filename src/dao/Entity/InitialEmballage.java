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
@Table(name="INITIAL_EMBALLAGE")
@NamedQuery(name="InitialEmballage.findAll", query="SELECT e FROM InitialEmballage e")
public class InitialEmballage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_PRIX_OULMES")
	private PrixOulmes prixOulmes;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @Column(name="DATE_INITIAL")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateInitial;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @Column(name="QTE_EMBALLAGE")
        private BigDecimal qteEmballage ;
        
        @ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientMP clientMP;
        
        @ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Fournisseur fourisseur;
      
        
        
	public InitialEmballage() {
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

	

        public BigDecimal getQteEmballage() {
        return qteEmballage;
        }

        public void setQteEmballage(BigDecimal qteEmballage) {
        this.qteEmballage = qteEmballage;
        }

        public Date getDateCreation() {
        return dateCreation;
        }

    public Date getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(Date dateInitial) {
        this.dateInitial = dateInitial;
    }

        public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
        }

        public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
        }

    public ClientMP getClientMP() {
        return clientMP;
    }

    public void setClientMP(ClientMP clientMP) {
        this.clientMP = clientMP;
    }

    public Fournisseur getFourisseur() {
        return fourisseur;
    }

    public void setFourisseur(Fournisseur fourisseur) {
        this.fourisseur = fourisseur;
    }

        public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
        }


	
	

}