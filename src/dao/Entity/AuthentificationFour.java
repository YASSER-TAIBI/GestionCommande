package dao.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="AuthentificationFour.findAll", query="SELECT u FROM AuthentificationFour u")
public class AuthentificationFour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nom;

	private String code;
        
	@Column(name="DATE_CREATION")
	private Date dateCreation;
        
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private AuthentificationFour utilisateurCreation;

    public AuthentificationFour getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(AuthentificationFour utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

           


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

        
}