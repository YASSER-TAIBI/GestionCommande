package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.CategorieMpDAO;
import dao.Entity.CategorieMp;
import org.hibernate.Query;

public class CategorieMpDAOImpl implements CategorieMpDAO {
	
	Session session=HibernateUtil.openSession();

	public  CategorieMp findById(int id) {
		return ( CategorieMp)session.get( CategorieMp.class, id);
		}

        public void add(CategorieMp e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CategorieMp edit(CategorieMp e) {
		
	session.beginTransaction();
	CategorieMp p= (CategorieMp)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(CategorieMp e) {
		
		session.beginTransaction();

		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	@Override
	public List<CategorieMp> findAll() {
		return session.createQuery("select c from CategorieMp c").list();
		}

        
         public List<CategorieMp> findCategorieMpByID(int id) {
		
		Query query = session.createQuery("select u from CategorieMp u where u.subCategorieMp.id =:id ");
		query.setParameter("id", id);
                return query.list();
}
         
         public List<CategorieMp> findCategorieMpByBox(String box) {
		
		Query query = session.createQuery("select u from CategorieMp u where u.nom like =:BOX");
		query.setParameter("BOX", box+"%");
                return query.list();
}
         
                public CategorieMp findCategorieMpByCat(String cat) {
		
		Query query = session.createQuery("select u from CategorieMp u where u.nom=:cat");
		query.setParameter("cat", cat);
                return (CategorieMp)query.uniqueResult();
}
}