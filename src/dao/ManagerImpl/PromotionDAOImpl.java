package dao.ManagerImpl;

import dao.Entity.Promotion;
import dao.Manager.PromotionDAO;
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
public class PromotionDAOImpl implements PromotionDAO {
     Session session=HibernateUtil.openSession();

     public Promotion findById(int id) {
		return (Promotion)session.get(Promotion.class, id);
		}

 
    
	public void add(Promotion promotion) {
		session.beginTransaction();
		session.save(promotion);
		
		session.getTransaction().commit();
		//return p;
	}


	public Promotion edit(Promotion e) {
		
	session.beginTransaction();
	Promotion p= (Promotion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Promotion e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<Promotion> findAll() {
        
              return session.createQuery("select c from Promotion c").list();
              
    }

    public Promotion findByNumCommandeAndNumLivraison(String numLivraison, String cmd) {
		
		Query query = session.createQuery("select c from Promotion c where c.livraisonFour =:numLivraison and c.numCommande =:cmd");
		query.setParameter("numLivraison",numLivraison);
                query.setParameter("cmd",cmd);
                
                return (Promotion) query.uniqueResult();
    }
    
    
}
