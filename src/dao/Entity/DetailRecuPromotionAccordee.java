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
@Table(name="DETAIL_RECU_PROMOTION_ACCORDEE")
@NamedQuery(name="DetailRecuPromotionAccordee.findAll", query="SELECT c FROM DetailRecuPromotionAccordee c")
public class DetailRecuPromotionAccordee implements Serializable {
	private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
        
        @Column(name="DATE_FACTURE")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateFacture;
        
        @Column(name="NUM_FACTURE")
        private String numFacture;
         
        @Column(name="RECU_FACTURE")
	private BigDecimal recuFacture ;
       
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @ManyToOne
        @JoinColumn(name = "ID_RECU_PROMOTION_ACCORDEE")
        private RecuPromotionAccordee recuPromotionAccordee;
        

    public DetailRecuPromotionAccordee() {
    }
    
    public int getId() {
        return id;
    }

    public Date getDateFacture() {
        return dateFacture;
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

    public RecuPromotionAccordee getRecuPromotionAccordee() {
        return recuPromotionAccordee;
    }

    public void setRecuPromotionAccordee(RecuPromotionAccordee recuPromotionAccordee) {
        this.recuPromotionAccordee = recuPromotionAccordee;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setId(int id) {
        this.id = id;
    }

     public BigDecimal getRecuFacture() {
        return recuFacture;
    }

    public void setRecuFacture(BigDecimal recuFacture) {
        this.recuFacture = recuFacture;
    }


   

 

}