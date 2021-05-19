package dao.ManagerImpl;

import dao.Entity.RecuPromotionAccordee;
import dao.Manager.RecuPromotionAccordeeDAO;
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
public class RecuPromotionAccordeeDAOImpl implements RecuPromotionAccordeeDAO {
     Session session=HibernateUtil.openSession();

     public RecuPromotionAccordee findById(int id) {
		return (RecuPromotionAccordee)session.get(RecuPromotionAccordee.class, id);
		}

 
    
	public void add(RecuPromotionAccordee recuPromotionAccordee) {
		session.beginTransaction();
		session.save(recuPromotionAccordee);
		
		session.getTransaction().commit();
		//return p;
	}


	public RecuPromotionAccordee edit(RecuPromotionAccordee e) {
		
	session.beginTransaction();
	RecuPromotionAccordee p= (RecuPromotionAccordee)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(RecuPromotionAccordee e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<RecuPromotionAccordee> findAll() {
        
              return session.createQuery("select c from RecuPromotionAccordee c").list();
              
    }

              public List<RecuPromotionAccordee>  findByEtat (String etat) {

		Query query= session.createQuery("select c from RecuPromotionAccordee c where c.etat =:etat");
		query.setParameter("etat", etat);
                
		return query.list();
}
    
}
