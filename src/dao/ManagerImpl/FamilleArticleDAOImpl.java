package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.FamilleArticleDAO;
import dao.Entity.FamilleArticle;
import org.hibernate.Query;

public class FamilleArticleDAOImpl implements FamilleArticleDAO {
	
	Session session=HibernateUtil.openSession();

	public  FamilleArticle findById(int id) {
		return ( FamilleArticle)session.get( FamilleArticle.class, id);
		}

        public void add(FamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FamilleArticle edit(FamilleArticle e) {
		
	session.beginTransaction();
	FamilleArticle p= (FamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(FamilleArticle e) {
		
		session.beginTransaction();

		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	@Override
	public List<FamilleArticle> findAll() {
		return session.createQuery("select c from FamilleArticle c").list();
		}

        
         public List<FamilleArticle> findCategorieMpByID(int id) {
		
		Query query = session.createQuery("select u from CategorieMp u where u.subCategorieMp.id =:id ");
		query.setParameter("id", id);
                return query.list();
}
         

}