/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Grammage;
import dao.Manager.GrammageDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class GrammageDAOImpl implements GrammageDAO  {
    Session session=HibernateUtil.openSession();




	public Grammage findById(int id) {
		return (Grammage)session.get(Grammage.class, id);
		}

	public void add(Grammage grammage) {
		session.beginTransaction();
		session.save(grammage);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public Grammage edit(Grammage e) {
		
	session.beginTransaction();
	Grammage p= (Grammage)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Grammage e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Grammage> findAll() {
		return session.createQuery("select c from Grammage c").list();
		}

    
}
