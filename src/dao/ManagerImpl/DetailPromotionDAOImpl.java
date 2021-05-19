package dao.ManagerImpl;

import dao.Entity.DetailPromotion;
import dao.Manager.DetailPromotionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import java.math.BigDecimal;

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
public class DetailPromotionDAOImpl implements DetailPromotionDAO {
     Session session=HibernateUtil.openSession();

     public DetailPromotion findById(int id) {
		return (DetailPromotion)session.get(DetailPromotion.class, id);
		}

 
    
	public void add(DetailPromotion detailPromotion) {
		session.beginTransaction();
		session.save(detailPromotion);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailPromotion edit(DetailPromotion e) {
		
	session.beginTransaction();
	DetailPromotion p= (DetailPromotion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailPromotion e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailPromotion> findAll() {
              return session.createQuery("select c from DetailPromotion c").list();
    }

          public BigDecimal findByMontantPromo(int mois) {
        Query query=  session.createQuery("select COALESCE(SUM(c.montant),0) from DetailPromotion c where YEAR(c.promotion.dateLivraison)= 2021 and MONTH(c.promotion.dateLivraison)=:mois ");
        query.setParameter("mois", mois);    
        
        return (BigDecimal) query.uniqueResult();
    }  
    
}
