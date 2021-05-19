/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
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
import javax.persistence.Temporal;

/**
 *
 * @author h
 */
@Entity
@NamedQuery(name="Fournisseur.findAll", query="SELECT u FROM Fournisseur u")
public class Fournisseur implements Serializable {
	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String nom ; 
    private String code;
    
    @ManyToOne
    @JoinColumn(name="id_ville")
    private Ville ville = new Ville();
    
    
    
    private String adresse;
    private String tel; 
    private String email;
    private Date dateCreation;
    private Date dateMAJ;
    
    @Column(name="TYPE_FOURNISSEUR")
    private String typeFournisseur;
    
    @Column(name="FOURNISSEUR")
    private String fournisseur;

    @ManyToOne
    @JoinColumn(name="ID_COMPTE")
    private CompteFourMP compteFourMP;
    
    
       @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    
    @Column(name="DELAI_DE_PAIEMENT")
     private int delaiPaiement;
    
    
    
    
    
       
       
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public CompteFourMP getCompteFourMP() {
        return compteFourMP;
    }

    public void setCompteFourMP(CompteFourMP compteFourMP) {
        this.compteFourMP = compteFourMP;
    }

    public String getTypeFournisseur() {
        return typeFournisseur;
    }

    public void setTypeFournisseur(String typeFournisseur) {
        this.typeFournisseur = typeFournisseur;
    }

    public Fournisseur() {
       
    }

    public int getDelaiPaiement() {
        return delaiPaiement;
    }

    public void setDelaiPaiement(int delaiPaiement) {
        this.delaiPaiement = delaiPaiement;
    }


    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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



    
}
