package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Produit;
import dao.Manager.ProduitDAO;


public class ProduitDAOImpl implements ProduitDAO {
	Session session=HibernateUtil.openSession();

	public void add(Produit e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Produit edit(Produit e) {
		
	session.beginTransaction();
	Produit p= (Produit)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(Produit e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<Produit> findAll() {
		return session.createQuery("select c from Produit c where c.etat = 'Lancé'").list();
		}


	public Produit findByCode(String code) {
		// TODO Auto-generated method stub
		Produit produit= new Produit();
		Query query= session.createQuery("select c from Produit c where c.code=:code and c.etat = 'Lancé'");
		query.setParameter("code", code);
		
		produit= (Produit) query.uniqueResult();
		
		return produit;
	}
        
        	 public List<Produit> findByCodeProduit(String code) {
		
		Query query = session.createQuery("select u from Produit u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<Produit> findBylibelleProduit(String lib) {
		
		Query query = session.createQuery("select u from Produit u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }

}
