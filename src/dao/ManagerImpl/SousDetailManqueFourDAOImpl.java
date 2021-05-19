package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.SousDetailManqueFour;
import dao.Manager.SousDetailManqueFourDAO;


public class SousDetailManqueFourDAOImpl implements SousDetailManqueFourDAO {
	Session session=HibernateUtil.openSession();

	public void add(SousDetailManqueFour e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SousDetailManqueFour edit(SousDetailManqueFour e) {
		
	session.beginTransaction();
	SousDetailManqueFour p= (SousDetailManqueFour)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(SousDetailManqueFour e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<SousDetailManqueFour> findAll() {
		return session.createQuery("select c from SousDetailManqueFour c").list();
		}

         
	public SousDetailManqueFour findById(int id) {
		return (SousDetailManqueFour)session.get(SousDetailManqueFour.class, id);
		}

      	 public List<SousDetailManqueFour> findByNumManque(String nMnq) {
		
		Query query = session.createQuery("select u from SousDetailManqueFour u where u.numRetour=:nMnq ");
		query.setParameter("nMnq",nMnq);
		
                return query.list();
 }

}
