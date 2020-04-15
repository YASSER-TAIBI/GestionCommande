package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@Table(name="stockArt")
@NamedQuery(name="StockArt.findAll", query="SELECT s FROM StockArt s")
public class StockArt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="quantite_commande")
	private int quantiteCommande;

	private BigDecimal stock;
        
	@Column(name="stock_min")
	private int stockMin;
	
	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_PRIX_OULMES")
	private PrixOulmes prixOulmes;
	
	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin ;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;

        
        
        
        
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }


	public StockArt() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(int quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }





}