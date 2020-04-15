
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

/**
 *
 * @author Hp
 */
    @Entity
    @NamedQuery(name="Dimension.findAll", query="SELECT u FROM Dimension u")
    public class Dimension  implements Serializable{
    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name="CODE")
    private String code;
      
    @Column(name="FAMILLE")
    private String famille;
      
    @ManyToOne
    @JoinColumn(name="ID_GATEGORIE")
    private CategorieMp categorieMp;
        
    @Column(name="LIBELLE")
    private String libelle;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
        
       
        
     public Dimension() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategorieMp getCategorieMp() {
        return categorieMp;
    }

    public void setCategorieMp(CategorieMp categorieMp) {
        this.categorieMp = categorieMp;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
     
     
}
