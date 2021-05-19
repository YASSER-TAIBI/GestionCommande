package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 * The persistent class for the employe database table.
 * 
 */
@Entity
@Table(name="REMISE_FIN_PERIODE")
@NamedQuery(name="RemiseFinPeriode.findAll", query="SELECT c FROM RemiseFinPeriode c")
public class RemiseFinPeriode implements Serializable {
	private static final long serialVersionUID = 1L;

        
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="ANNEE")
        private String annee;

	@Column(name="ETAT")
        private String etat;
        
        @ManyToOne
        @JoinColumn(name="ID_UTIL_CREATION")
        private Utilisateur utilisateurCreation;
        
        @Column(name="DATE_CREATION")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;
        
        @OneToMany(cascade = CascadeType.ALL,mappedBy="remiseFinPeriode")
        private List<DetailRemiseFinPeriode> detailRemiseFinPeriode;
        
        
	public RemiseFinPeriode() {
	}

        public int getId() {
        return id;
        }

        public void setId(int id) {
        this.id = id;
        }

        public String getAnnee() {
        return annee;
        }

        public void setAnnee(String annee) {
        this.annee = annee;
        }

        public String getEtat() {
        return etat;
        }

        public void setEtat(String etat) {
        this.etat = etat;
        }

        public List<DetailRemiseFinPeriode> getDetailRemiseFinPeriode() {
        return detailRemiseFinPeriode;
        }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

        public void setDetailRemiseFinPeriode(List<DetailRemiseFinPeriode> detailRemiseFinPeriode) {
        this.detailRemiseFinPeriode = detailRemiseFinPeriode;
        }

}