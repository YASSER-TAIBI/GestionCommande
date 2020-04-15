package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.SousFamilleArticleDAO;
import dao.Entity.SousFamilleArticle;
import org.hibernate.Query;

public class SousFamilleArticleDAOImpl implements SousFamilleArticleDAO {
	
	Session session=HibernateUtil.openSession();

	public  SousFamilleArticle findById(int id) {
		return ( SousFamilleArticle)session.get( SousFamilleArticle.class, id);
		}

        public void add(SousFamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SousFamilleArticle edit(SousFamilleArticle e) {
		
	session.beginTransaction();
	SousFamilleArticle p= (SousFamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(SousFamilleArticle e) {
		
		session.beginTransaction();

		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	@Override
	public List<SousFamilleArticle> findAll() {
		return session.createQuery("select c from SousFamilleArticle c").list();
		}

        

         

}