package dao.ManagerImpl;

import dao.Entity.DetailRecuPromotionAccordee;
import dao.Manager.DetailRecuPromotionAccordeeDAO;
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
public class DetailRecuPromotionAccordeeDAOImpl implements DetailRecuPromotionAccordeeDAO {
     Session session=HibernateUtil.openSession();

     public DetailRecuPromotionAccordee findById(int id) {
		return (DetailRecuPromotionAccordee)session.get(DetailRecuPromotionAccordee.class, id);
		}

 
    
	public void add(DetailRecuPromotionAccordee detailRecuPromotionAccordee) {
		session.beginTransaction();
		session.save(detailRecuPromotionAccordee);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailRecuPromotionAccordee edit(DetailRecuPromotionAccordee e) {
		
	session.beginTransaction();
	DetailRecuPromotionAccordee p= (DetailRecuPromotionAccordee)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailRecuPromotionAccordee e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailRecuPromotionAccordee> findAll() {
        
              return session.createQuery("select c from DetailRecuPromotionAccordee c").list();
              
    }

    public List<DetailRecuPromotionAccordee> findByRecuPromotionAccordee(int idRecuPromotionAccordee) {
		
		Query query = session.createQuery("select u from DetailRecuPromotionAccordee u where u.recuPromotionAccordee.id=:idRecuPromotionAccordee");
                query.setParameter("idRecuPromotionAccordee", idRecuPromotionAccordee);
                
	return  query.list();
				} 
    
}
