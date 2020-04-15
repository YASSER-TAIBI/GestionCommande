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
@Table(name="INTERVALLE")
@NamedQuery(name="Intervalle.findAll", query="SELECT c FROM Intervalle c")
public class Intervalle implements Serializable {
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    @Column(name = "ID")
    private int id;

    @Column(name="CODE")
    private String code;
   
    @Column(name="VALEUR_MAX")
    private int valeurMax;
    
    @Column(name="VALEUR_MIN")
    private int valeurMin;    

    @Column(name="LIBELLE")
    private String libelle;

       @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
    
    
    
    
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public void setValeurMax(int valeurMax) {
        this.valeurMax = valeurMax;
    }

    public int getValeurMin() {
        return valeurMin;
    }

    public void setValeurMin(int valeurMin) {
        this.valeurMin = valeurMin;
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
}
