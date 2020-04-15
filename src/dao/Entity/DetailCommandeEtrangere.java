/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 *
 * @author h
 */
@Entity
@Table(name="DETAIL_COMMANDE_ETRANGERE")
@NamedQuery(name="DetailCommandeEtrangere.findAll", query="SELECT u FROM DetailCommandeEtrangere u")
public class DetailCommandeEtrangere implements Serializable {
    
    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMAJ;

    @ManyToOne
    @JoinColumn(name="ID_ARTICLE")
    private Article article;
    
    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private ClientMP clientMP;
    
    @Column(name="QUANTITE")
    private BigDecimal quantite ;
    
    @Column(name="FAMILLE")
    private String famille ;
    
    @Column(name="SOUS_FAMILLE")
    private String sousFamille ;
    
    @Column(name="TYPE_ARTICLE")
    private String typeArt ;
    
    @Column(name="CODE_FOURNISSEUR")
    private String codeFournisseur;
    
    @Column(name = "QTE_LIVREE")
    private BigDecimal quantiteLivree;
    
    @Column(name="QUANTITE_Restant")
    private BigDecimal quantiteRestant ;
    
//    @Column(name = "QTE_APPLIQUE_1")
//    private BigDecimal quantiteApplique1;
//    
//    @Column(name = "QTE_APPLIQUE_2")
//    private BigDecimal quantiteApplique2;
//    
//    @Column(name = "VALEUR_DOUANE_1")
//    private BigDecimal valeurDouane1;
//    
//    @Column(name = "VALEUR_DOUANE_2")
//    private BigDecimal valeurDouane2;
//    
//    @Column(name = "VALEUR_Global")
//    private BigDecimal valeurGlobal;
//    
//    @Column(name = "TAUX_DOUANE")
//    private BigDecimal tauxDouane;
    
//    @Column(name = "VALEUR_A_PAYER")
//    private BigDecimal valeurPayer;
    
//    @Column(name = "MOUNTANT_UNITAIRE")
//    private BigDecimal montantUnitaire;
    
    @ManyToOne
    @JoinColumn(name = "ID_TAUX_DEVISE")
    private TauxDevise tauxDevise;
    
    @Column(name="PRIX")
    private BigDecimal prix;
    
    @Column(name="PRIX_DEVISE")
    private BigDecimal prixDevise;
    
    @Column(name="NBR_CARTON")
    private BigDecimal nbrCarton;
    
    @Column(name="POIDS_CARTON")
    private BigDecimal poidsCarton;
    
    @Column(name="MONTANT")
    private BigDecimal montant ;

    @ManyToOne
    @JoinColumn(name = "ID_COMMANDE_ETRANGERE")
    private CommandeEtrangere commandeEtrangere;

    @OneToMany(cascade ={CascadeType.ALL},fetch = FetchType.EAGER,mappedBy = "detailCommandeEtrangere")
    private List<DetailReceptionEtrangere> detailReceptionEtrangere= new ArrayList<DetailReceptionEtrangere>();
    
    
    
    
    
    
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public BigDecimal getQuantiteLivree() {
        return quantiteLivree;
    }

    public void setQuantiteLivree(BigDecimal quantiteLivree) {
        this.quantiteLivree = quantiteLivree;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public ClientMP getClientMP() {
        return clientMP;
    }

    public void setClientMP(ClientMP clientMP) {
        this.clientMP = clientMP;
    }

    public List<DetailReceptionEtrangere> getDetailReceptionEtrangere() {
        return detailReceptionEtrangere;
    }

    public BigDecimal getNbrCarton() {
        return nbrCarton;
    }

    public void setNbrCarton(BigDecimal nbrCarton) {
        this.nbrCarton = nbrCarton;
    }

    public BigDecimal getPoidsCarton() {
        return poidsCarton;
    }

    public void setPoidsCarton(BigDecimal poidsCarton) {
        this.poidsCarton = poidsCarton;
    }

    public void setDetailReceptionEtrangere(List<DetailReceptionEtrangere> detailReceptionEtrangere) {
        this.detailReceptionEtrangere = detailReceptionEtrangere;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getQuantiteRestant() {
        return quantiteRestant;
    }

    public void setQuantiteRestant(BigDecimal quantiteRestant) {
        this.quantiteRestant = quantiteRestant;
    }

    public BigDecimal getPrixDevise() {
        return prixDevise;
    }

    public void setPrixDevise(BigDecimal prixDevise) {
        this.prixDevise = prixDevise;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
    public BigDecimal getMontant() {
        return montant;
    }

    public TauxDevise getTauxDevise() {
        return tauxDevise;
    }

    public void setTauxDevise(TauxDevise tauxDevise) {
        this.tauxDevise = tauxDevise;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
     public int getId() {
        return id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public CommandeEtrangere getCommandeEtrangere() {
        return commandeEtrangere;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getSousFamille() {
        return sousFamille;
    }

    public void setSousFamille(String sousFamille) {
        this.sousFamille = sousFamille;
    }

    public String getTypeArt() {
        return typeArt;
    }

    public void setTypeArt(String typeArt) {
        this.typeArt = typeArt;
    }

    public void setCommandeEtrangere(CommandeEtrangere commandeEtrangere) {
        this.commandeEtrangere = commandeEtrangere;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
//
//    public BigDecimal getQuantiteApplique1() {
//        return quantiteApplique1;
//    }
//
//    public void setQuantiteApplique1(BigDecimal quantiteApplique1) {
//        this.quantiteApplique1 = quantiteApplique1;
//    }
//
//    public BigDecimal getQuantiteApplique2() {
//        return quantiteApplique2;
//    }
//
//    public void setQuantiteApplique2(BigDecimal quantiteApplique2) {
//        this.quantiteApplique2 = quantiteApplique2;
//    }
//
//    public BigDecimal getValeurDouane1() {
//        return valeurDouane1;
//    }
//
//    public void setValeurDouane1(BigDecimal valeurDouane1) {
//        this.valeurDouane1 = valeurDouane1;
//    }
//
//    public BigDecimal getValeurDouane2() {
//        return valeurDouane2;
//    }
//
//    public void setValeurDouane2(BigDecimal valeurDouane2) {
//        this.valeurDouane2 = valeurDouane2;
//    }
//
//    public BigDecimal getValeurGlobal() {
//        return valeurGlobal;
//    }
//
//    public void setValeurGlobal(BigDecimal valeurGlobal) {
//        this.valeurGlobal = valeurGlobal;
//    }
//
//    public BigDecimal getTauxDouane() {
//        return tauxDouane;
//    }
//
//    public void setTauxDouane(BigDecimal tauxDouane) {
//        this.tauxDouane = tauxDouane;
//    }
//
//    public BigDecimal getValeurPayer() {
//        return valeurPayer;
//    }
//
//    public void setValeurPayer(BigDecimal valeurPayer) {
//        this.valeurPayer = valeurPayer;
//    }
//
//    public BigDecimal getMontantUnitaire() {
//        return montantUnitaire;
//    }
//
//    public void setMontantUnitaire(BigDecimal montantUnitaire) {
//        this.montantUnitaire = montantUnitaire;
//    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

  
}
