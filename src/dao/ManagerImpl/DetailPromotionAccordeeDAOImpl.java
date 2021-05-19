package dao.ManagerImpl;

import dao.Entity.DetailPromotionAccordee;
import dao.Manager.DetailPromotionAccordeeDAO;
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
public class DetailPromotionAccordeeDAOImpl implements DetailPromotionAccordeeDAO {
     Session session=HibernateUtil.openSession();

     public DetailPromotionAccordee findById(int id) {
		return (DetailPromotionAccordee)session.get(DetailPromotionAccordee.class, id);
		}

 
    
	public void add(DetailPromotionAccordee detailPromotionAccordee) {
		session.beginTransaction();
		session.save(detailPromotionAccordee);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailPromotionAccordee edit(DetailPromotionAccordee e) {
		
	session.beginTransaction();
	DetailPromotionAccordee p= (DetailPromotionAccordee)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailPromotionAccordee e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailPromotionAccordee> findAll() {
        
              return session.createQuery("select c from DetailPromotionAccordee c").list();
              
    }

         public BigDecimal findByMontantPromoAcco(int mois) {
        Query query=  session.createQuery("select COALESCE(SUM(c.montant),0) from DetailPromotionAccordee c where YEAR(c.promotionAccordee.datePromotion)= 2021 and MONTH(c.promotionAccordee.datePromotion)=:mois ");
        query.setParameter("mois", mois);    
        
        return (BigDecimal) query.uniqueResult();
    }  
    
                 public List<DetailPromotionAccordee>  findByPromotionAccordee(int idPromotionAccordee) {
		
		Query query = session.createQuery("select u from DetailPromotionAccordee u where u.promotionAccordee.id =:idPromotionAccordee");
                query.setParameter("idPromotionAccordee", idPromotionAccordee);
                
	return  query.list();
				} 
                 
                  public List<DetailPromotionAccordee>  findByPromotionAccordee(int idPromotionAccordee, String code) {
		
		Query query = session.createQuery("select u from DetailPromotionAccordee u where u.promotionAccordee.id =:idPromotionAccordee and u.prixOulmes.produit.code =:code");
                query.setParameter("idPromotionAccordee", idPromotionAccordee);
                query.setParameter("code", code);

	return  query.list();
				} 
         
}
