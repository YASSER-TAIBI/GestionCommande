package dao.Entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * 
 * 
 */
@Entity
@Table(name="SOUS_FAMILLE_ARTICLE")
@NamedQuery(name="SousFamilleArticle.findAll", query="SELECT c FROM SousFamilleArticle c")
public class SousFamilleArticle implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;

        @ManyToOne
	@JoinColumn(name="ID_FAMILLE_ARTICLE")
	private FamilleArticle familleArticle;

        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
	//bi-directional many-to-one association to MatierePremier

	public SousFamilleArticle() {
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

    public FamilleArticle getFamilleArticle() {
        return familleArticle;
    }

    public void setFamilleArticle(FamilleArticle familleArticle) {
        this.familleArticle = familleArticle;
    }
     

        
        
}