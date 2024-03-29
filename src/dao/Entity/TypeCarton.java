/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
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
@Table(name="TYPE_CARTON")
@NamedQuery(name="TypeCarton.findAll", query="SELECT c FROM TypeCarton c")
public class TypeCarton  implements Serializable{
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    @Column(name = "ID")
    private int id;
    
    @Column(name="LIBELLE")
    private String libelle;
    
    @Column(name="CODE")
    private String code;

    @Column(name="ETAT")
    private String etat;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

       
       
       
       
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
