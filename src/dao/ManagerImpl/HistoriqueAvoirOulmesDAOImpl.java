package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.HistoriqueAvoirOulmes;
import dao.Manager.HistoriqueAvoirOulmesDAO;


public class HistoriqueAvoirOulmesDAOImpl implements HistoriqueAvoirOulmesDAO {
	Session session=HibernateUtil.openSession();

	public void add(HistoriqueAvoirOulmes e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public HistoriqueAvoirOulmes edit(HistoriqueAvoirOulmes e) {
		
	session.beginTransaction();
	HistoriqueAvoirOulmes p= (HistoriqueAvoirOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(HistoriqueAvoirOulmes e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<HistoriqueAvoirOulmes> findAll() {
		return session.createQuery("select c from HistoriqueAvoirOulmes c").list();
		}

	public HistoriqueAvoirOulmes findById(int id) {
		return (HistoriqueAvoirOulmes)session.get(HistoriqueAvoirOulmes.class, id);
		}

     }

