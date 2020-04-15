/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_COMPTE")
@NamedQuery(name = "DetailCompte.findAll", query = "SELECT u FROM DetailCompte u")
public class DetailCompte implements Serializable {
       private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
     @Column(name="DATE_OPERATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateOperation;
     
      @Column(name="DATE_BON_LIVRAISON")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateBonLivraison;
     
     @Column(name="DESIGNATION")
    private String designation ;
     
     @Column(name="CODE_OPERATION")
    private String code ;
     
     @Column(name="MONTANT_CREDIT")
    private BigDecimal montantCredit;
    
    @Column(name="MONTANT_DEBIT")
    private BigDecimal montantDebit;
    
      @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    @ManyToOne
    @JoinColumn(name = "id_CompteFourMP")
    private CompteFourMP compteFourMP;

   
   @ManyToOne
    @JoinColumn(name = "id_ClientMP")
    private ClientMP clientMP;

   
   
   
   
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public CompteFourMP getCompteFourMP() {
        return compteFourMP;
    }

    public void setCompteFourMP(CompteFourMP compteFourMP) {
        this.compteFourMP = compteFourMP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getDateBonLivraison() {
        return dateBonLivraison;
    }

    public void setDateBonLivraison(Date dateBonLivraison) {
        this.dateBonLivraison = dateBonLivraison;
    }

    
      public ClientMP getClientMP() {
        return clientMP;
    }

    public void setClientMP(ClientMP clientMP) {
        this.clientMP = clientMP;
    }

    
}
