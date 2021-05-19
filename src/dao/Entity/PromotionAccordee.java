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
@Table(name="PROMOTION_ACCORDEE")
@NamedQuery(name="PromotionAccordee.findAll", query="SELECT e FROM PromotionAccordee e")
public class PromotionAccordee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="NUM_BON")
	private String numBon;

        @Column(name="CLIENT")
        private String client ;
        
        @Column(name="CODE_CLIENT")
        private String codeClient ;

        @Column(name="MONTANT_TOTAL")
        private BigDecimal montantTotal ;
        
        @Column(name="DATE_PROMOTION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date datePromotion;

        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
    
        @ManyToOne
        @JoinColumn(name="ID_DEPOT")
        private Depot depot;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @OneToMany(cascade = CascadeType.ALL,mappedBy="promotionAccordee")
        private List<DetailPromotionAccordee> detailPromotionAccordee;

        
        
	public PromotionAccordee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getNumBon() {
        return numBon;
    }

    public void setNumBon(String numBon) {
        this.numBon = numBon;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String CodeClient) {
        this.codeClient = CodeClient;
    }

    public Date getDatePromotion() {
        return datePromotion;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setDatePromotion(Date datePromotion) {
        this.datePromotion = datePromotion;
    }

    public List<DetailPromotionAccordee> getDetailPromotionAccordee() {
        return detailPromotionAccordee;
    }

    public void setDetailPromotionAccordee(List<DetailPromotionAccordee> detailPromotionAccordee) {
        this.detailPromotionAccordee = detailPromotionAccordee;
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