/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
 * @author Hp
 */

public class HistoriquePrixGlobal  implements Serializable{
 
        
    private int id;

    private BigDecimal ancienPrix ;

    private BigDecimal nouveauPrix ;

    private CategorieMp categorieMp ;

    private PrixOulmes prixOulmes;
      
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAncienPrix() {
        return ancienPrix;
    }

    public void setAncienPrix(BigDecimal ancienPrix) {
        this.ancienPrix = ancienPrix;
    }

    public BigDecimal getNouveauPrix() {
        return nouveauPrix;
    }

    public void setNouveauPrix(BigDecimal nouveauPrix) {
        this.nouveauPrix = nouveauPrix;
    }

    public PrixOulmes getPrixOulmes() {
        return prixOulmes;
    }

    public void setPrixOulmes(PrixOulmes prixOulmes) {
        this.prixOulmes = prixOulmes;
    }

    public CategorieMp getCategorieMp() {
        return categorieMp;
    }

    public void setCategorieMp(CategorieMp categorieMp) {
        this.categorieMp = categorieMp;
    }

}
