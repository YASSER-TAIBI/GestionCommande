package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@Table(name="stockPF")
@NamedQuery(name="StockPF.findAll", query="SELECT s FROM StockPF s")
public class StockPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

        private BigDecimal manque;
        
        @Column(name="STOCK")
	private BigDecimal stock;

	@Column(name="STOCK_MIN")
	private BigDecimal stockMin;
	
	@Column(name="QUANTITE_COMMANDE")
	private BigDecimal qteCommande;
	
	@Column(name="PRIX_UNITAIRE")
	private BigDecimal prixUnitaire;

        @ManyToOne
        @JoinColumn(name = "id_mat_pre")
        private MatierePremier matierePremier;
        
	@ManyToOne
        @JoinColumn(name="id_magasin")
        private Magasin magasin;
		
        
		

	public StockPF() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

        public BigDecimal getQteCommande() {
        return qteCommande;
        }

        public void setQteCommande(BigDecimal qteCommande) {
        this.qteCommande = qteCommande;
            }
        
        public Magasin getMagasin() {
        return magasin;
        }

        public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
        }


        public MatierePremier getMatierePremier() {
        return matierePremier;
        }

        public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
        }

	public BigDecimal getStockMin() {
		return this.stockMin;
	}

        public BigDecimal getManque() {
        return manque;
        }

        public void setManque(BigDecimal manque) {
        this.manque = manque;
        }
	public void setStockMin(BigDecimal stockMin) {
		this.stockMin = stockMin;
	}
	
	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}




	
	

}