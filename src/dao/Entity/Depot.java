package dao.Entity;

import java.io.Serializable;
import java.util.ArrayList;
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
 * The persistent class for the machine database table.
 * 
 */
@Entity
@NamedQuery(name = "Depot.findAll", query = "SELECT m FROM Depot m")
public class Depot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String code;

	private String libelle;

	// bi-directional many-to-one association to RipFournisseur
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "depot")
	private List<Magasin> ListMagasin= new ArrayList<>();
	
        @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

	public Depot() {
	}

	public Magasin addMagasin(Magasin magasin) {
		getListMagasin().add(magasin);
		magasin.setDepot(this);

		return magasin;
	}

	public Magasin removeMagasin(Magasin magasin) {
		getListMagasin().remove(magasin);
		magasin.setDepot(null);

		return magasin;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

	public List<Magasin> getListMagasin() {
		return ListMagasin;
	}

	public void setListMagasin(List<Magasin> listMagasin) {
		ListMagasin = listMagasin;
	}

}