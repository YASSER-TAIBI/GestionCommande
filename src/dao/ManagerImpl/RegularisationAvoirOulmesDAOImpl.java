package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.RegularisationAvoirOulmes;
import dao.Manager.RegularisationAvoirOulmesDAO;


public class RegularisationAvoirOulmesDAOImpl implements RegularisationAvoirOulmesDAO {
	Session session=HibernateUtil.openSession();

	public void add(RegularisationAvoirOulmes e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public RegularisationAvoirOulmes edit(RegularisationAvoirOulmes e) {
		
	session.beginTransaction();
	RegularisationAvoirOulmes p= (RegularisationAvoirOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(RegularisationAvoirOulmes e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<RegularisationAvoirOulmes> findAll() {
		return session.createQuery("select c from RegularisationAvoirOulmes c").list();
		}

	public RegularisationAvoirOulmes findById(int id) {
		return (RegularisationAvoirOulmes)session.get(RegularisationAvoirOulmes.class, id);
		}

          public List<RegularisationAvoirOulmes> findRegularisationAvoirOulmesBybonAvoir(String bonAvoir) {
        Query query=  session.createQuery("select u from RegularisationAvoirOulmes u where u.bonAvoir=:bonAvoir");
        query.setParameter("bonAvoir", bonAvoir);
        
        return query.list();
    }
}

