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
@Table(name="REGLEMENT")
@NamedQuery(name="Reglement.findAll", query="SELECT c FROM Reglement c")
public class Reglement implements Serializable {
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date date;
    
    @Column(name="DATE_ECHEANCE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateEcheance;
    
    @Column(name="CODE_REGLEMENT")
    private String codeReglement;
    
    @Column(name="MONTANT_REGLEMENT")
    private BigDecimal montantReglement;
    
    @Column(name="MONTANT_ECART")
    private BigDecimal montantEcart;
    
    @Column(name="MONTANT_AVANCE")
    private BigDecimal montantAvance;
    
    @Column(name="MONTANT_ANONYME")
    private BigDecimal montantAnonyme;
    
    @ManyToOne
     @JoinColumn(name="ID_FOURNISSEUR")
     private Fournisseur fournisseur  ;
   
    @Column(name="NUM_FACTURE")
    private String numFacture;

    @Column(name="NUM_CRITIQUE")
    private String numCritique;
  
    @Column(name="MODE_REGLEMENT")
    private String modeReglement;

    @Column(name="COMMENTAIRES")
    private String commentaires;
    
     @ManyToOne
     @JoinColumn(name="ID_CLIENT")
     private ClientMP clientMP  ;

     @Column(name="DESIGNATION")
    private String designation;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    private Boolean action;
      
      
      
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public BigDecimal getMontantEcart() {
        return montantEcart;
    }

    public void setMontantEcart(BigDecimal montantEcart) {
        this.montantEcart = montantEcart;
    }

    public BigDecimal getMontantAvance() {
        return montantAvance;
    }

    public void setMontantAvance(BigDecimal montantAvance) {
        this.montantAvance = montantAvance;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
   
    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public String getNumCritique() {
        return numCritique;
    }

    public void setNumCritique(String numCritique) {
        this.numCritique = numCritique;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public BigDecimal getMontantAnonyme() {
        return montantAnonyme;
    }

    public void setMontantAnonyme(BigDecimal montantAnonyme) {
        this.montantAnonyme = montantAnonyme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCodeReglement() {
        return codeReglement;
    }

    public void setCodeReglement(String codeReglement) {
        this.codeReglement = codeReglement;
    }

    public BigDecimal getMontantReglement() {
        return montantReglement;
    }

    public void setMontantReglement(BigDecimal montantReglement) {
        this.montantReglement = montantReglement;
    }

 public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    
  public ClientMP getClientMP() {
        return clientMP;
    }

    public void setClientMP(ClientMP clientMP) {
        this.clientMP = clientMP;
    }
    
     public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
