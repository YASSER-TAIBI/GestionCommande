/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Hp
 */
@Entity
@Table(name="BON_RETOUR")
@NamedQuery(name="BonRetour.findAll", query="SELECT c FROM BonRetour c")
public class BonRetour implements Serializable {
     private static final long serialVersionUID = 1L;
    
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="N_RETOUR")
    private String numRetour;
    
    @Column(name="N_COMMANDE")
    private String numCommande;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    
    @Column(name="DATE_PAIEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePaiement;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="N_LIVRAISON")
    private String numLivraison;
    
    @Column(name="MOTIF")
    private String motif;
    
     @Column(name="N_FACTURE")
    private String numFacture;
    
    @Column(name="MONTANT_REGLER")
    private BigDecimal montantRegler;
     
    @Column(name="MONTANT_TOTAL")
    private BigDecimal montantTotal;
    
    @Column(name="MONTANT_FACTURE")
    private BigDecimal montantFacture;
    
    @Column(name="MONTANT")
    private BigDecimal montant;
    
    @Column(name="MONTANT_PAYE")
    private BigDecimal montantPaye;
    
    @Column(name="FOURNISSEUR")
    private String fournisseur;

    @Column(name="CLIENT")
    private String client;
    
    @Column(name="STATUT")
    private String statut;
    
    @Column(name="N_TRAITE")
    private String numTraite;
     
    @OneToMany(cascade = CascadeType.ALL,mappedBy="bonRetour")
    private List<DetailBonRetour> detailBonRetour;
     
    @Column(name="RECEPTION_OR_USINE")
    private String receptionOrUsine;
    
    @Column(name="EN_STOCK")
    private String enStock;
      
    @Column(name="TYPE")
    private String type;

    @Column(name="Etat")
    private String etat;
   
    private Boolean action;
    
    
    
    
  
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnCommande() {
        return numCommande;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public void setnCommande(String nCommande) {
        this.numCommande = nCommande;
    }

    public BigDecimal getMontantRegler() {
        return montantRegler;
    }

    public void setMontantRegler(BigDecimal montantRegler) {
        this.montantRegler = montantRegler;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Boolean getAction() {
        return action;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNumTraite() {
        return numTraite;
    }

    public void setNumTraite(String numTraite) {
        this.numTraite = numTraite;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public String getnLivraison() {
        return numLivraison;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public String getNumRetour() {
        return numRetour;
    }

    public String getReceptionOrUsine() {
        return receptionOrUsine;
    }

    public void setReceptionOrUsine(String receptionOrUsine) {
        this.receptionOrUsine = receptionOrUsine;
    }

    public String getEnStock() {
        return enStock;
    }

    public void setEnStock(String enStock) {
        this.enStock = enStock;
    }

    public void setNumRetour(String numRetour) {
        this.numRetour = numRetour;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public BigDecimal getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNumLivraison() {
        return numLivraison;
    }

    public void setNumLivraison(String numLivraison) {
        this.numLivraison = numLivraison;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public void setnLivraison(String nLivraison) {
        this.numLivraison = nLivraison;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getnRetour() {
        return numRetour;
    }

    public void setnRetour(String nRetour) {
        this.numRetour = nRetour;
    }

    
      public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<DetailBonRetour> getDetailBonRetour() {
        return detailBonRetour;
    }

    public void setDetailBonRetour(List<DetailBonRetour> detailBonRetour) {
        this.detailBonRetour = detailBonRetour;
    } 
    
       public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
