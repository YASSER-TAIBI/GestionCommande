package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the employe database table.
 * 
 */

public class SituationRecuPromoAcco implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int mois;

	private BigDecimal rfpEnAtt;

	private BigDecimal recu ;
        
        private BigDecimal promoAcco;

        
	public SituationRecuPromoAcco() {
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

    public BigDecimal getRfpEnAtt() {
        return rfpEnAtt;
    }

    public void setRfpEnAtt(BigDecimal rfpEnAtt) {
        this.rfpEnAtt = rfpEnAtt;
    }

    public BigDecimal getRecu() {
        return recu;
    }

    public void setRecu(BigDecimal recu) {
        this.recu = recu;
    }

    public BigDecimal getPromoAcco() {
        return promoAcco;
    }

    public void setPromoAcco(BigDecimal promoAcco) {
        this.promoAcco = promoAcco;
    }

   

 

}