package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Entity.Magasin;
import dao.Manager.MagasinDAO;

public class MagasinDAOImpl implements MagasinDAO {
	Session session=HibernateUtil.openSession();

	public void add(Magasin e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Magasin edit(Magasin e) {
		
	session.beginTransaction();
	Magasin p= (Magasin)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Magasin p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<Magasin> findAll() {
		return session.createQuery("select c from Magasin c").list();
		}

	public Magasin findById(int id) {
		return (Magasin)session.get(Magasin.class, id);
		}
        
        public Magasin findStockByMagasinMP(int id) {
		// TODO Auto-generated method stub
		Magasin magasin= new Magasin();
		Query query= session.createQuery("select c from Magasin c where c.depot.id =:id");
		query.setParameter("id", id);
		
		magasin= (Magasin) query.uniqueResult();
		
		return magasin;
	}
	
}
