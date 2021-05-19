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
    
    @Column(name="ETAT")
    private String etat;

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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    
}
