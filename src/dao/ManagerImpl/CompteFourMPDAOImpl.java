/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.CompteFourMP;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Manager.CompteFourMPDAO;
import org.hibernate.Query;

/**
 *
 * @author Hp
 */
public class CompteFourMPDAOImpl implements CompteFourMPDAO {
     Session session=HibernateUtil.openSession();

    
    
	public CompteFourMP findById(int id) {
		return (CompteFourMP)session.get(CompteFourMP.class, id);
		}

   
	public void add(CompteFourMP compteFourMP) {
		session.beginTransaction();
		session.save(compteFourMP);
		
		session.getTransaction().commit();
		
	}


	public CompteFourMP edit(CompteFourMP e) {
		
	session.beginTransaction();
	CompteFourMP p= (CompteFourMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(CompteFourMP e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<CompteFourMP> findAll() {
              return session.createQuery("select c from CompteFourMP c").list();
    }

 

}
