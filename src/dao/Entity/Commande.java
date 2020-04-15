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
import javax.persistence.Temporal;

/**
 *
 * @author h
 */
@Entity
@NamedQuery(name="Commande.findAll", query="SELECT u FROM Commande u")
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Object nCommandeColumn;
  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="N_COMMANDE")
    private String nCommande;
    
  
    @Column(name="DATE_SAISIE")
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateSaisie;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMAJ;
    
    @Column(name="ETAT")
    private String etat;
    
  
    @ManyToOne
    @JoinColumn(name="ID_DEPOT")
    private Depot depot;

    @Column(name="TYPE_COMMANDE")
    private String typeCommande;
    
    
    
    

    //bi-directional many-to-one association to DetailCommande  fetch = FetchType.EAGER , orphanRemoval = true
	@OneToMany(mappedBy="commande",cascade = {CascadeType.ALL})
	private List<DetailCommande> detailCommandes= new ArrayList<DetailCommande>();
        
          //bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientMP clientMP;
        
        @ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Fournisseur fourisseur;

        
     @Column(name="DATE_VALIDATION")
     @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateValidation;
     
     
     @Column(name="UTIL_VALIDATION")
     private String utilValidation; 

   
     @Column(name="DATE_ENVOI")
     @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateEnvoi;
     
     
    @Column(name="UTIL_ENVOI")
    private String utilEnvoi;

        
    @Column(name="DATE_RECU")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRecu;
     
     
    @Column(name="UTIL_RECU")
    private String utilRecu;
    
    @Column(name="PRIX_TOTAL")
    private BigDecimal prixTotal ;

    
    
    public Date getDateRecu() {
        return dateRecu;
    }

    public void setDateRecu(Date dateRecu) {
        this.dateRecu = dateRecu;
    }

    public String getUtilRecu() {
        return utilRecu;
    }

    public void setUtilRecu(String utilRecu) {
        this.utilRecu = utilRecu;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public String getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }


    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getUtilEnvoi() {
        return utilEnvoi;
    }

    public void setUtilEnvoi(String utilEnvoi) {
        this.utilEnvoi = utilEnvoi;
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
    
    public Commande(){}

    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DetailCommande> getDetailCommandes() {
        return detailCommandes;
    }

    public void setDetailCommandes(List<DetailCommande> detailCommandes) {
        this.detailCommandes = detailCommandes;
    }
    
    public String getnCommande() {
        return nCommande;
    }

    public void setnCommande(String nCommande) {
        this.nCommande = nCommande;
    }

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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public static Object getnCommandeColumn() {
        return nCommandeColumn;
    }

    public static void setnCommandeColumn(Object nCommandeColumn) {
        Commande.nCommandeColumn = nCommandeColumn;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getUtilValidation() {
        return utilValidation;
    }

    public void setUtilValidation(String utilValidation) {
        this.utilValidation = utilValidation;
    }


   
    
}
