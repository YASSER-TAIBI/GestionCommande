package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.DetailManqueFour;
import dao.Manager.DetailManqueFourDAO;


public class DetailManqueFourDAOImpl implements DetailManqueFourDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailManqueFour e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailManqueFour edit(DetailManqueFour e) {
		
	session.beginTransaction();
	DetailManqueFour p= (DetailManqueFour)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailManqueFour e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<DetailManqueFour> findAll() {
		return session.createQuery("select c from DetailManqueFour c").list();
		}

        
        public List<DetailManqueFour> findByStatutMP() {
		return session.createQuery("select c from DetailManqueFour c where c.statut='Mp'").list();
		}
        
        public List<DetailManqueFour> findByStatutPF() {
		return session.createQuery("select c from DetailManqueFour c where c.statut='Pf'").list();
		}
        
        public List<DetailManqueFour>  findDetailManqueFourByNumCom(String numCom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailManqueFour c where c.numCommande=:numCom");
		query.setParameter("numCom", numCom);
		
		return query.list();

}
        
             public DetailManqueFour findDetailManqueFourByNumComAndNumBL(String numCom, String numBl) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailManqueFour c where c.numCommande=:numCom and c.numBonLiv=:numBl");
		query.setParameter("numCom", numCom);
		query.setParameter("numBl", numBl);
		return (DetailManqueFour) query.uniqueResult();

}
        
	public DetailManqueFour findById(int id) {
		return (DetailManqueFour)session.get(DetailManqueFour.class, id);
		}



}
