/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.BonRetour;
import dao.Manager.BonRetourDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class BonRetourDAOImpl implements BonRetourDAO{
    Session session=HibernateUtil.openSession();





    
	public BonRetour findById(int id) {
		return (BonRetour)session.get(BonRetour.class, id);
		}

   
	public void add(BonRetour bonRetour) {
		session.beginTransaction();
		session.save(bonRetour);
		
		session.getTransaction().commit();
		//return p;
	}


	public BonRetour edit(BonRetour e) {
		
	session.beginTransaction();
	BonRetour p= (BonRetour)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(BonRetour e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<BonRetour> findAll() {
              return session.createQuery("select c from BonRetour c").list();
    }

	
                public List<BonRetour> findTypeByRechercheBonRetourMP(String typ, String typ1) {
                Query query = session.createQuery("select c from BonRetour c where c.statut ='Mp' and (c.type =:typ or c.type =:typ1)");
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
                
                  public List<BonRetour> findTypeByRechercheBonRetourPF(String typ, String typ1) {
                Query query = session.createQuery("select c from BonRetour c where c.statut ='Pf' and (c.type =:typ or c.type =:typ1)");
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
                  
                  public List<BonRetour> findTypeByRechercheBonRetour(String typ, String typ1) {
                Query query = session.createQuery("select c from BonRetour c where (c.type =:typ or c.type =:typ1)");
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
                
                public BonRetour findBonRetourByNumCommAndNumLiv(String nCom ,String nRtr, String etat) {
		Query query = session.createQuery("select u from BonRetour u where u.numCommande =:nCom and u.numRetour =:nRtr and u.etat=:etat");
		query.setParameter("nCom",nCom);
                query.setParameter("etat",etat);
                query.setParameter("nRtr",nRtr);
		
                return (BonRetour)query.uniqueResult();
 }
                     
    
                public List<BonRetour> findBonRetourByRechercheFourAndClient(String four ,String client, String typ, String typ1) {
		
		Query query = session.createQuery("select u from BonRetour u where u.fournisseur =:four and u.client =:client and (u.type =:typ or u.type =:typ1)");
		query.setParameter("four",four);
                query.setParameter("client",client);
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);

                return query.list();
 }
              
                
                
                           public List<BonRetour> findBonRetourByFilterMP(String typ, String typ1 , String req) {
                Query query = session.createQuery("select c from BonRetour c where c.statut ='Mp' and (c.type =:typ or c.type =:typ1)"+req );
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
                               public List<BonRetour> findBonRetourByFilterPF(String typ, String typ1 , String req) {
                Query query = session.createQuery("select c from BonRetour c where c.statut ='Pf' and (c.type =:typ or c.type =:typ1)"+req );
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
}
