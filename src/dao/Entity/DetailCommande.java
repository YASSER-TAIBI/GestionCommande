/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author h
 */
@Entity
@Table(name = "DETAIL_COMMANDE")
@NamedQuery(name = "DetailCommande.findAll", query = "SELECT u FROM DetailCommande u")

public class DetailCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    public static DetailCommande get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    private BigDecimal prixUnitaire;

    @Column(name = "Quantite")
    private BigDecimal quantite ;
    
    @Column(name = "REMISE_ACHAT")
    private BigDecimal remiseAchat ;
    
    @Column(name="ETAT")
    private String etat;
    
    @Column(name="MAGASIN")
    private String magasin;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @Column(name = "QTE_RECU")
    private BigDecimal quantiteRecu ;

    @Column(name = "QTE_RESTEE")
    private BigDecimal quantiteRestee;

    @Column(name = "QTE_LIVREE")
    private BigDecimal quantiteLivree;

    //bi-directional many-to-one association to MatierePremier
   
    @ManyToOne
    @JoinColumn(name = "id_mat_pre")
    private MatierePremier matierePremier;

     @ManyToOne
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;
    
    //bi-directional many-to-one association to Commande cascade = {CascadeType.ALL}
 
    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

//    (cascade = {CascadeType.ALL})
    @ManyToOne
    @JoinColumn(name = "DIMENSION")
    private Dimension dimension = new Dimension();

    @OneToMany(cascade ={CascadeType.ALL},fetch = FetchType.EAGER,mappedBy = "detailCommande")
    private List<DetailReception> detailReception= new ArrayList<DetailReception>();

    
    
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    
    
    
    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(BigDecimal quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }

    public BigDecimal getQuantiteRestee() {
        return quantiteRestee;
    }

    public void setQuantiteRestee(BigDecimal quantiteRestee) {
        this.quantiteRestee = quantiteRestee;
    }

    public BigDecimal getQuantiteLivree() {
        return quantiteLivree;
    }

    public void setQuantiteLivree(BigDecimal quantiteLivree) {
        this.quantiteLivree = quantiteLivree;
    }

    public List<DetailReception> getDetailReception() {
        return detailReception;
    }

    public void setDetailReception(List<DetailReception> detailReception) {
        this.detailReception = detailReception;
    }

    
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public DetailCommande() {
    }

    public BigDecimal getRemiseAchat() {
        return remiseAchat;
    }

    public void setRemiseAchat(BigDecimal remiseAchat) {
        this.remiseAchat = remiseAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    public MatierePremier getMatierePremier() {
        return matierePremier;
    }

    public void setMatierePremier(MatierePremier matierePremier) {
        this.matierePremier = matierePremier;
    }

    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    public Commande getCommande() {
        return commande;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }


    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

//    @Transient
//    public BigDecimal getCalculeQuantiteRecu() {
//        BigDecimal result= getQuantiteRecu().add(getQuantiteLivree()).setScale(2, RoundingMode.FLOOR);
//        System.out.println("qte recu"+getQuantiteRecu());
//          System.out.println("qte livree"+getQuantiteLivree());
//         return result;
//    }
//    
//    @Transient
//    public BigDecimal getCalculeQuantiteRestee() {
//        BigDecimal result= getQuantite().subtract(getQuantiteRecu().add(getQuantiteLivree())).setScale(2, RoundingMode.FLOOR);
//         System.out.println("qte"+getQuantite());
//          System.out.println("qte recu calcul"+getCalculeQuantiteRecu());
//         return result ;
//  
//    }
    
    

}
