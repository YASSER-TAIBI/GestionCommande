/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Hp
 */
@Entity
@Table(name ="TAUX_DEVISE")
@NamedQuery(name="TauxDevise.findAll", query="SELECT u FROM TauxDevise u")
public class TauxDevise implements Serializable {
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name="CODE")
    private String code;
      
    @Column(name="DEVISE_1")
    private String devise1;
    
    @Column(name="DEVISE_2")
    private String devise2;
    
    @Column(name="DEVISE_MONTANT")
    private BigDecimal deviseMontant;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;  

    @ManyToOne
    @JoinColumn(name="ID_DEVISE")
    private Devise devise;  
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
        
        
     public TauxDevise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDevise1() {
        return devise1;
    }

    public void setDevise1(String devise1) {
        this.devise1 = devise1;
    }

    public String getDevise2() {
        return devise2;
    }

    public void setDevise2(String devise2) {
        this.devise2 = devise2;
    }

    public BigDecimal getDeviseMontant() {
        return deviseMontant;
    }

    public void setDeviseMontant(BigDecimal deviseMontant) {
        this.deviseMontant = deviseMontant;
    }



}
