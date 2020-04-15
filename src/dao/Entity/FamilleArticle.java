package dao.Entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * 
 * 
 */
@Entity
@Table(name="FAMILLE_ARTICLE")
@NamedQuery(name="FamilleArticle.findAll", query="SELECT c FROM FamilleArticle c")
public class FamilleArticle implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;


        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
	//bi-directional many-to-one association to MatierePremier
	@OneToMany(cascade = CascadeType.ALL,mappedBy="familleArticle")
	private List<SousFamilleArticle> sousFamilleArticle;

	public FamilleArticle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public void setNom(String nom) {
		this.nom = nom;
    }

    public List<SousFamilleArticle> getSousFamilleArticle() {
        return sousFamilleArticle;
    }

    public void setSousFamilleArticle(List<SousFamilleArticle> sousFamilleArticle) {
        this.sousFamilleArticle = sousFamilleArticle;
    }




}