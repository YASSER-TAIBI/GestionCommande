package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Inventaire;
import dao.Manager.InventaireDAO;


public class InventaireDAOImpl implements InventaireDAO {
	Session session=HibernateUtil.openSession();

	public void add(Inventaire e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Inventaire edit(Inventaire e) {
		
	session.beginTransaction();
	Inventaire p= (Inventaire)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(Inventaire e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<Inventaire> findAll() {
		return session.createQuery("select c from Inventaire c").list();
		}

        
	public Inventaire findById(int id) {
		return (Inventaire)session.get(Inventaire.class, id);
		}

               public List<Inventaire> findByAllInventaire(String req) {
		
		Query query = session.createQuery("select c from Inventaire c where c.id <> 0"+req);
                return query.list();
 }
        
}
