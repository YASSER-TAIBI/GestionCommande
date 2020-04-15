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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author h
 */
@Entity
@NamedQuery(name="ClientMP.findAll", query="SELECT u FROM ClientMP u")
public class ClientMP implements Serializable{
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nom;
    private String code;
    private String codeDepot;
    private String adresse;
    private String tel;
    private String email;
    private String typeClient;
    private Date dateCreation;
    private Date dateMAJ;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="clientMP")
    private List<Commande> Commande;
    
    public ClientMP() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
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

    public String getCodeDepot() {
        return codeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        this.codeDepot = codeDepot;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMAJUtili() {
        return dateMAJ;
    }

    public void setDateMAJUtili(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }
    
}
