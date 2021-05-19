package dao.ManagerImpl;

import dao.Entity.PromotionAccordee;
import dao.Manager.PromotionAccordeeDAO;
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
public class PromotionAccordeeDAOImpl implements PromotionAccordeeDAO {
     Session session=HibernateUtil.openSession();

     public PromotionAccordee findById(int id) {
		return (PromotionAccordee)session.get(PromotionAccordee.class, id);
		}

 
    
	public void add(PromotionAccordee promotionAccordee) {
		session.beginTransaction();
		session.save(promotionAccordee);
		
		session.getTransaction().commit();
		//return p;
	}


	public PromotionAccordee edit(PromotionAccordee e) {
		
	session.beginTransaction();
	PromotionAccordee p= (PromotionAccordee)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PromotionAccordee e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<PromotionAccordee> findAll() {
        
              return session.createQuery("select c from PromotionAccordee c").list();
              
    }

                public List<String> findByClient() {
      Query query = session.createQuery("select DISTINCT u.client from PromotionAccordee u ");
        return query.list();
    }
                   
       public List<PromotionAccordee> findByAllPromotionAccordee(String req) {
		
		Query query = session.createQuery("select c from PromotionAccordee c where c.id <> 0"+req);
                return query.list();
 }
                   
}
