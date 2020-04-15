/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailBonLivraison;
import dao.Manager.DetailBonLivraisonDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 *
 */
public class DetailBonLivraisonDAOImpl implements DetailBonLivraisonDAO {
    Session session=HibernateUtil.openSession();


	

	public DetailBonLivraison findById(int id) {
		return (DetailBonLivraison)session.get(DetailBonLivraison.class, id);
		}

	public void add(DetailBonLivraison e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailBonLivraison edit(DetailBonLivraison e) {
		
	session.beginTransaction();
	DetailBonLivraison p= (DetailBonLivraison)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailBonLivraison e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailBonLivraison> findAll() {
		return session.createQuery("select c from DetailBonRetour c").list();
		}
	
//    public List<DetailBonLivraison>  findDetaiBonLivraisonBycode (String code) {
//
//		Query query= session.createQuery("select c from DetailBonLivraison c where c.livraisonFour=:code");
//		query.setParameter("code", code);
//		return query.list();
//}

    
        public List<DetailBonLivraison>  findDetaiBonLivraisonBycode (String code , String cmd) {

		Query query= session.createQuery("select c from DetailBonLivraison c where c.livraisonFour=:code and c.numCommande =:cmd");
		query.setParameter("code", code);
                query.setParameter("cmd", cmd);
		return query.list();
}
        
    public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}

    public DetailBonLivraison findDetailBonLivraisonByDetailCommande(String numCom,String numLiv, int mp) {
		
		Query query = session.createQuery("select u from DetailBonLivraison u where  u.numCommande =:numCom and u.livraisonFour =:numLiv and u.matierePremier.id =:mp ");
		query.setParameter("numCom", numCom);
		query.setParameter("numLiv", numLiv);
                query.setParameter("mp", mp);
	return (DetailBonLivraison) query.uniqueResult();
	      
				}
    
           public List<DetailBonLivraison> findCommandeByDetailBonLivraison(String ncom ) {
		
		Query query = session.createQuery("select c from DetailBonLivraison c where c.numCommande =:ncom" );
		query.setParameter("ncom",ncom);
        
                return query.list();
                
 }
    
    
    
}
