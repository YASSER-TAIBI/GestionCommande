/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name="COMPTE_FOUR_MP")
@NamedQuery(name="CompteFourMP.findAll", query="SELECT c FROM CompteFourMP c")
public class CompteFourMP implements Serializable {
      private static final long serialVersionUID = 1L;
      
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    @Column(name="CODE")
    private String code;
    
    @Column(name="LIBELLE")
    private String libelle;
    
    @Column(name="MONTANT_CREDIT")
    private BigDecimal montantCredit;
    
    @Column(name="MONTANT_DEBIT")
    private BigDecimal montantDebit;
    
     @Column(name="Solde_Report")
    private BigDecimal soldeReport;

    @Column(name="SOLDE")
    private BigDecimal solde;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    
  @OneToMany(cascade = CascadeType.ALL,mappedBy="compteFourMP")
	private List<DetailCompte> detailCompte;

    public List<DetailCompte> getDetailCompte() {
        return detailCompte;
    }

    public void setDetailCompte(List<DetailCompte> detailCompte) {
        this.detailCompte = detailCompte;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }

    public BigDecimal getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(BigDecimal montantDebit) {
        this.montantDebit = montantDebit;
    }

    public BigDecimal getSoldeReport() {
        return soldeReport;
    }

    public void setSoldeReport(BigDecimal soldeReport) {
        this.soldeReport = soldeReport;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

  
    
        @Transient
    public BigDecimal getTotal() {
        BigDecimal result =  getSolde().add(getSoldeReport());
         return result.setScale(2, RoundingMode.HALF_UP) ;
    }
}
