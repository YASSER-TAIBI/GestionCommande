package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the employe database table.
 * 
 */

@Entity
@Table(name="RECU_PROMOTION_ACCORDEE")
@NamedQuery(name="RecuPromotionAccordee.findAll", query="SELECT c FROM RecuPromotionAccordee c")
public class RecuPromotionAccordee implements Serializable {
	private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        @Column(name="ANNEE")
        private String annee;
        
        @Column(name="MOIS")
	private int mois;

        @Column(name="RFP_EN_ATT")
        private BigDecimal rfpEnAtt;
        
        @Column(name="RECU")
	private BigDecimal recu ;
        
        @Column(name="TOTAL_RECU")
	private BigDecimal totalRecu ;
        
        @Column(name="RECU_FACTURE")
	private BigDecimal recuFacture ;
        
        @Column(name="PROMOTION_ACCORDEE")
        private BigDecimal promoAcco;

        @Column(name="ETAT")
        private String etat;
        
        @OneToMany(cascade = CascadeType.ALL,mappedBy="recuPromotionAccordee")
        private List<DetailRecuPromotionAccordee> detailRecuPromotionAccordee;
        
        
        
    public RecuPromotionAccordee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public String getEtat() {
        return etat;
    }

    public BigDecimal getTotalRecu() {
        return totalRecu;
    }

    public void setTotalRecu(BigDecimal totalRecu) {
        this.totalRecu = totalRecu;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public BigDecimal getRecu() {
        return recu;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public BigDecimal getRecuFacture() {
        return recuFacture;
    }

    public List<DetailRecuPromotionAccordee> getDetailRecuPromotionAccordee() {
        return detailRecuPromotionAccordee;
    }

    public void setDetailRecuPromotionAccordee(List<DetailRecuPromotionAccordee> detailRecuPromotionAccordee) {
        this.detailRecuPromotionAccordee = detailRecuPromotionAccordee;
    }

    public void setRecuFacture(BigDecimal recuFacture) {
        this.recuFacture = recuFacture;
    }

    public void setRecu(BigDecimal recu) {
        this.recu = recu;
    }

    public BigDecimal getRfpEnAtt() {
        return rfpEnAtt;
    }

    public void setRfpEnAtt(BigDecimal rfpEnAtt) {
        this.rfpEnAtt = rfpEnAtt;
    }

    public BigDecimal getPromoAcco() {
        return promoAcco;
    }

    public void setPromoAcco(BigDecimal promoAcco) {
        this.promoAcco = promoAcco;
    }

}