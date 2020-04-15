/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Devise;
import dao.Manager.DeviseDAO;

/**
 *
 * @author Hp
 */
public class DeviseDAOImpl implements DeviseDAO  {
    Session session=HibernateUtil.openSession();




	public Devise findById(int id) {
		return (Devise)session.get(Devise.class, id);
		}

	public void add(Devise Devise) {
		session.beginTransaction();
		session.save(Devise);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public Devise edit(Devise e) {
		
	session.beginTransaction();
	Devise p= (Devise)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Devise e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Devise> findAll() {
		return session.createQuery("select c from Devise c").list();
		}


	

}
