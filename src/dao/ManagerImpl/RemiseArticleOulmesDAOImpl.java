package dao.ManagerImpl;

import dao.Entity.RemiseArticleOulmes;
import dao.Manager.RemiseArticleOulmesDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

import java.util.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class RemiseArticleOulmesDAOImpl implements RemiseArticleOulmesDAO {
     Session session=HibernateUtil.openSession();

     public RemiseArticleOulmes findById(int id) {
		return (RemiseArticleOulmes)session.get(RemiseArticleOulmes.class, id);
		}

 
    
	public void add(RemiseArticleOulmes remiseArticleOulmes) {
		session.beginTransaction();
		session.save(remiseArticleOulmes);
		
		session.getTransaction().commit();
		//return p;
	}


	public RemiseArticleOulmes edit(RemiseArticleOulmes e) {
		
	session.beginTransaction();
	RemiseArticleOulmes p= (RemiseArticleOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(RemiseArticleOulmes e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<RemiseArticleOulmes> findAll() {
        
              return session.createQuery("select c from RemiseArticleOulmes c").list();
              
    }  
}
