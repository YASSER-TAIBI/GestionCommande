package dao.Entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_categorie_mp database table.
 * 
 */
@Entity
@Table(name="sub_categorie_mp")
@NamedQuery(name="SubCategorieMp.findAll", query="SELECT s FROM SubCategorieMp s")
public class SubCategorieMp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;
	
	private String unite;
        
        @Column(name="ETAT")
        private String etat;
        
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

	//bi-directional many-to-one association to CategorieMp
	@OneToMany(mappedBy="subCategorieMp")
	private List<CategorieMp> categorieMps;

	public SubCategorieMp() {
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<CategorieMp> getCategorieMps() {
		return this.categorieMps;
	}

	public void setCategorieMps(List<CategorieMp> categorieMps) {
		this.categorieMps = categorieMps;
	}

	public CategorieMp addCategorieMp(CategorieMp categorieMp) {
		getCategorieMps().add(categorieMp);
		categorieMp.setSubCategorieMp(this);

		return categorieMp;
	}

	public CategorieMp removeCategorieMp(CategorieMp categorieMp) {
		getCategorieMps().remove(categorieMp);
		categorieMp.setSubCategorieMp(null);

		return categorieMp;
	}

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

}