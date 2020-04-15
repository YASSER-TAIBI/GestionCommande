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
@Table(name = "PRIX_CARTON")
@NamedQuery(name="PrixCarton.findAll", query="SELECT u FROM PrixCarton u")
public class PrixCarton implements Serializable{
    	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    
     
        @ManyToOne
	@JoinColumn(name="ID_TYPE_CARTON")
	private TypeCarton typeCarton;
    
        @ManyToOne
	@JoinColumn(name="ID_INTERVALLE")
	private Intervalle intervalle;
        
        @ManyToOne
	@JoinColumn(name="ID_SUB_CATEGORIE_MP")
	private SubCategorieMp SubCategorieMp ;
    
        @ManyToOne
	@JoinColumn(name="ID_CATEGORIE_MP")
	private CategorieMp categorieMp ;
    
        @ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Fournisseur fournisseur;
    
        @ManyToOne
	@JoinColumn(name="ID_DIMENSION")
	private Dimension dimension;
      
        @Column(name="PRIX")
        private BigDecimal prix ;

        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;

        @Column(name="TYPE_PRIX")
        private String typePrix;
            
            
            
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

    public TypeCarton getTypeCarton() {
        return typeCarton;
    }

    public void setTypeCarton(TypeCarton typeCarton) {
        this.typeCarton = typeCarton;
    }

    public SubCategorieMp getSubCategorieMp() {
        return SubCategorieMp;
    }

    public void setSubCategorieMp(SubCategorieMp SubCategorieMp) {
        this.SubCategorieMp = SubCategorieMp;
    }

    public CategorieMp getCategorieMp() {
        return categorieMp;
    }

    public void setCategorieMp(CategorieMp categorieMp) {
        this.categorieMp = categorieMp;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Intervalle getIntervalle() {
        return intervalle;
    }

    public void setIntervalle(Intervalle intervalle) {
        this.intervalle = intervalle;
    }

    public String getTypePrix() {
        return typePrix;
    }

    public void setTypePrix(String typePrix) {
        this.typePrix = typePrix;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    
    
}
