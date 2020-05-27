/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Commande;
import dao.Manager.CommandeDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

import dao.Entity.DetailCommande;
import java.util.Date;

/**
 *
 * @author h
 */
public class CommandeDAOImpl implements CommandeDAO{
    Session session=HibernateUtil.openSession();


public List<Commande>  findCommandeByEtat(String etat, String typeCommande) {
		
		Query query = session.createQuery("select u from Commande u where  u.etat=:etat and u.typeCommande =:typeCommande");
		query.setParameter("etat", etat);
                query.setParameter("typeCommande", typeCommande);

		
	return  query.list();
	      

				}
    
//	public List<Commande>  findCommandeByEtat(String etat, Depot codeDepot) {
//		
//		Query query = session.createQuery("select u from Commande u where  u.etat=:etat and u.depot:=codede");
//		query.setParameter("etat", etat);
//                query.setParameter("codede", codeDepot);
//		
//	return  query.list();
//	      
//
//				}

    
	public Commande findById(int id) {
		return (Commande)session.get(Commande.class, id);
		}

   
	public void add(Commande commande) {
		session.beginTransaction();
		session.save(commande);
		
		session.getTransaction().commit();
		//return p;
	}


	public Commande edit(Commande e) {
		
	session.beginTransaction();
	Commande p= (Commande)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Commande e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<Commande> findAll(String typeCommande) {
              Query query = session.createQuery("select c from Commande c where c.typeCommande=:typeCommande");
                query.setParameter("typeCommande", typeCommande);
		
	return  query.list();
    }

    

    public int getMaxId() {
          int id=0;
        Query query= session.createQuery("select Max(id) from Commande c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id+100;
    }
       
   public int getMaxIdSpecial() {
          int id=0;
        Query query= session.createQuery("select Max(id) from Commande c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }

	 public List<Commande> findFourByRechercheNumCommande(String nCommande,String etat) {
		
		Query query = session.createQuery("select u from Commande u where u.etat=:etat and u.nCommande like:nCommande and u.typeCommande='Interne'");
		query.setParameter("nCommande","%"+nCommande+"%");
                query.setParameter("etat",etat);
               
		
                return query.list();
 }

         	 public List<Commande> findFourByRechercheNumCommandeOulmes(String nCommande,String etat) {
		
		Query query = session.createQuery("select u from Commande u where u.etat=:etat and u.nCommande like:nCommande and u.typeCommande='Interne Art'");
		query.setParameter("nCommande","%"+nCommande+"%");
                query.setParameter("etat",etat);
               
		
                return query.list();
 }
  
               	 public Commande findCommandeByNumCommande(String nCommande,String etat) {
		
		Query query = session.createQuery("select u from Commande u where u.etat <> :etat and u.nCommande =:nCommande and u.typeCommande ='Interne'");
		query.setParameter("nCommande",nCommande);
                query.setParameter("etat",etat);
	
                return (Commande)query.uniqueResult();
 }
          
          public void delete(DetailCommande e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}
          
       public List<Commande> findByNumCommande(String nComDepot, String nComfour) {
		
		Query query = session.createQuery("select u from Commande u where nCommande like:nComDepot or nCommande like:nComfour");
		query.setParameter("nComDepot","%"+nComDepot+"%");
                query.setParameter("nComfour","%"+nComfour+"%");
                
               
		
                  return query.list();
 }
       
              public Commande findNumLivraisonByNumCommande() {
		
		Query query = session.createQuery("select u from Commande u where u.nCommande like:nComP or u.nCommande like:nComF or u.nCommande like:nComD");
                query.setParameter("nComP","%/% P%");
                query.setParameter("nComF","%/% F %");
                query.setParameter("nComD","%/% D %");
                   return (Commande)query.uniqueResult();
 }
         
                     public List<Commande> findByDateCommande(Date dateCreation ,String etat) {
		
		Query query = session.createQuery("select u from Commande u where u.dateCreation= :dateCreation and u.etat=:etat");
		query.setParameter("dateCreation", dateCreation);
                query.setParameter("etat",etat);
                  return query.list();
 }
	
}
