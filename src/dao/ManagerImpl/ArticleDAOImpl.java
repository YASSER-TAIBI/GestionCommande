package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Article;
import dao.Manager.ArticleDAO;


public class ArticleDAOImpl implements ArticleDAO {
	Session session=HibernateUtil.openSession();

	public void add(Article e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Article edit(Article e) {
		
	session.beginTransaction();
	Article p= (Article)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(Article e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<Article> findAll() {
		return session.createQuery("select c from Article c").list();
		}

	public Article findById(int id) {
		return (Article)session.get(Article.class, id);
		}


   public Article findArticleByCodeArt( String codeArt) {
		
		Query query = session.createQuery("select u from Article u where u.code =:codeArt");
                query.setParameter("codeArt", codeArt);
                
	return (Article)  query.uniqueResult();
        
				}



}
