package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.MatierePremiereDAO;
import dao.Entity.CategorieMp;
import dao.Entity.MatierePremier;
import javafx.collections.ListChangeListener;

public class MatierePremierDAOImpl implements MatierePremiereDAO {
	Session session=HibernateUtil.openSession();

	public void add(MatierePremier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MatierePremier edit(MatierePremier e) {
		
	session.beginTransaction();
	MatierePremier p= (MatierePremier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(MatierePremier e) {
		
		session.beginTransaction();
                
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<MatierePremier> findAll() {
		return session.createQuery("select c from MatierePremier c where c.etat = 'Lancé'").list();
		}

	public MatierePremier findById(int id) {
		return (MatierePremier)session.get(MatierePremier.class, id);
		}

	@Override
	public MatierePremier findByCode(String code) {
		// TODO Auto-generated method stub
		MatierePremier matierPremiere= new MatierePremier();
		Query query= session.createQuery("select c from MatierePremier c where c.code=:code and c.etat = 'Lancé'");
		query.setParameter("code", code);
		
		matierPremiere= (MatierePremier) query.uniqueResult();
		
		return matierPremiere;
	}

	@Override
	public List<MatierePremier> findMatierePremierSaufCatTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from MatierePremier c where CategorieMp.id <>1 and CategorieMp.id <> 2 and c.etat = 'Lancé'").list();
	}

	@Override
	public List<CategorieMp> listeCategorieTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from CategorieMp c where c.etat = 'Lancé' and code ='CH001' or code = 'HB001' ").list();
	}

	@Override
	public List<MatierePremier> listeMatierePremierByCategorie(String codeCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where CategorieMp.code =:codeCat and c.etat = 'Lancé'");
		query.setParameter("codeCat", codeCat);
		
		return query.list();
		
	}


        public List<MatierePremier> listeMatierePremierByMp(String Mp) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c WHERE c.code =:Mp");
		query.setParameter("Mp", Mp);
		
		return query.list();
		
	}

	
	    public List<MatierePremier> listeMatierePremierByNomMp(String nom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c WHERE c.nom like:nom ");
		query.setParameter("nom","%"+nom+"%");
		
		return query.list();
		
	}

}
