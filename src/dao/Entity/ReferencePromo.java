/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author h
 */
@Entity
@Table(name = "REFERENCE_PROMO")
@NamedQuery(name = "ReferencePromo.findAll", query = "SELECT u FROM ReferencePromo u")

public class ReferencePromo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static ReferencePromo get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DEFAUT_PROMO")
    private BigDecimal defautPromo ;
    
    @Column(name = "OBJECTIF_PROMO")
    private BigDecimal objectifPromo ;

    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

     @ManyToOne
    @JoinColumn(name = "ID_PRIX_OULMES")
    private PrixOulmes prixOulmes;
    
    @Column(name="ETAT")
    private String etat;

    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public BigDecimal getDefautPromo() {
        return defautPromo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDefautPromo(BigDecimal defautPromo) {
        this.defautPromo = defautPromo;
    }

    public BigDecimal getObjectifPromo() {
        return objectifPromo;
    }

    public void setObjectifPromo(BigDecimal objectifPromo) {
        this.objectifPromo = objectifPromo;
    }

    public ReferencePromo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }


//    @Transient
//    public BigDecimal getCalculeQuantiteRecu() {
//        BigDecimal result= getQuantiteRecu().add(getQuantiteLivree()).setScale(2, RoundingMode.FLOOR);
//        System.out.println("qte recu"+getQuantiteRecu());
//          System.out.println("qte livree"+getQuantiteLivree());
//         return result;
//    }
//    
//    @Transient
//    public BigDecimal getCalculeQuantiteRestee() {
//        BigDecimal result= getQuantite().subtract(getQuantiteRecu().add(getQuantiteLivree())).setScale(2, RoundingMode.FLOOR);
//         System.out.println("qte"+getQuantite());
//          System.out.println("qte recu calcul"+getCalculeQuantiteRecu());
//         return result ;
//  
//    }
    
    

}
