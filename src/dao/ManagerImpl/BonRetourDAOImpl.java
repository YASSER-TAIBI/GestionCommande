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

    
    @Override
    public int getMaxId() {
          int id;
        Query query= session.createQuery("select Max(id) from BonRetour c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id+1;
    }

                public List<BonRetour> findEtatByRechercheBonRetour(String etat) {
                Query query = session.createQuery("select c from BonRetour c where c.etat =:etat");
		query.setParameter("etat",etat);
   
                return query.list();
     }		
	
                public List<BonRetour> findTypeByRechercheBonRetour(String typ, String typ1) {
                Query query = session.createQuery("select c from BonRetour c where c.type =:typ or c.type =:typ1");
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);
   
                return query.list();
     }
             
                public BonRetour findBonRetourByNumRetour(String nRetour ,String etat) {
		Query query = session.createQuery("select u from BonRetour u where u.numRetour =:nRetour and u.type=:etat");
		query.setParameter("nRetour",nRetour);
                query.setParameter("etat",etat);
               
		
                return (BonRetour)query.uniqueResult();
 }
                     
                public List<BonRetour> findBonRetourByEtatAndRetMnq(String retMaq ,String etat) {
		Query query = session.createQuery("select c from BonRetour c where c.type =:retMaq and c.etat =:etat");
		query.setParameter("retMaq",retMaq);
                query.setParameter("etat",etat);
               
		
                return query.list();
 }
                
                public List<BonRetour> findBonRetourByEtatAndFour(String four ,String etat) {
		Query query = session.createQuery("select c from BonRetour c where c.fournisseur =:four and c.etat =:etat");
		query.setParameter("four",four);
                query.setParameter("etat",etat);
               
		
                return query.list();
 }
                
                public List<BonRetour> findBonRetourByEtatAndResUsi(String resUsi ,String etat) {
		Query query = session.createQuery("select c from BonRetour c where c.receptionOrUsine =:resUsi and c.etat =:etat");
		query.setParameter("resUsi",resUsi);
                query.setParameter("etat",etat);
               
		
                 return query.list();
 }
                
                public List<BonRetour> findBonRetourByEtatAndResUsiAndFour(String resUsi ,String etat, String four) {
		Query query = session.createQuery("select c from BonRetour c where c.fournisseur =:four and c.receptionOrUsine =:resUsi and c.etat =:etat");
		query.setParameter("resUsi",resUsi);
                query.setParameter("etat",etat);
                query.setParameter("four",four);
               
		
                 return query.list();
 }
                     
                public BonRetour findBonRetourByNumCommAndNumLiv(String nCom ,String nRtr, String etat, String resUsi) {
		Query query = session.createQuery("select u from BonRetour u where u.numCommande =:nCom and u.numRetour =:nRtr and u.etat=:etat and u.receptionOrUsine =:resUsi");
		query.setParameter("nCom",nCom);
                query.setParameter("etat",etat);
                query.setParameter("nRtr",nRtr);
                query.setParameter("resUsi",resUsi);
		
                return (BonRetour)query.uniqueResult();
 }
                     
                public List<BonRetour> findBonRetourByFourAndStockAndRec(String recUsi, String stock, String four,String retMaq) {
                Query query = session.createQuery("select c from BonRetour c where c.enStock =:stock and c.fournisseur=:four and c.receptionOrUsine =:recUsi and c.type=:retMaq");
                query.setParameter("recUsi",recUsi);
                query.setParameter("stock",stock);
                query.setParameter("four",four);
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
	
                public List<BonRetour> findBonRetourByFourAndRec(String recUsi, String four,String retMaq) {
	        Query query = session.createQuery("select c from BonRetour c where c.fournisseur=:four and c.receptionOrUsine =:recUsi and c.type=:retMaq");
                query.setParameter("recUsi",recUsi);
                query.setParameter("four",four);
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
                     
                public List<BonRetour> findBonRetourByRechercheFourAndClient(String four ,String client, String typ, String typ1) {
		
		Query query = session.createQuery("select u from BonRetour u where u.fournisseur =:four and u.client =:client and (u.type =:typ or u.type =:typ1)");
		query.setParameter("four",four);
                query.setParameter("client",client);
                query.setParameter("typ",typ);
                query.setParameter("typ1",typ1);

                return query.list();
 }
                 
                public List<BonRetour> findBonRetour(String retMaq) {
	        Query query = session.createQuery("select c from BonRetour c where c.type=:retMaq");
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
                                 
                public List<BonRetour> findBonRetourByFour(String four,String retMaq) {
                Query query = session.createQuery("select c from BonRetour c where c.fournisseur=:four and c.type=:retMaq");
                query.setParameter("four",four);
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
                
                public List<BonRetour> findBonRetourByRecUsine(String recUsi,String retMaq) {
                Query query = session.createQuery("select c from BonRetour c where c.receptionOrUsine =:recUsi and c.type=:retMaq");
                query.setParameter("recUsi",recUsi);
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
                
                public List<BonRetour> findBonRetourByStock(String stock,String retMaq) {
                Query query = session.createQuery("select c from BonRetour c where c.enStock =:stock and c.type=:retMaq");
                query.setParameter("stock",stock);
                query.setParameter("retMaq",retMaq);
                
                return query.list();
     }
                
                public List<BonRetour> findByFourAndRec(String recUsi, String four) {
                Query query = session.createQuery("select c from BonRetour c where c.fournisseur=:four and c.receptionOrUsine =:recUsi");
                query.setParameter("recUsi",recUsi);
                query.setParameter("four",four);

                
                return query.list();
     }
                                
                   public List<BonRetour> findByFour(String four) {
                Query query = session.createQuery("select c from BonRetour c where c.fournisseur=:four");
                query.setParameter("four",four);

                
                return query.list();
     }
                   
                   public List<BonRetour> findBonRetourByFourAndStockAndRecAndEtat(String recUsi, String stock, String four,String retMaq,String etat) {
                Query query = session.createQuery("select c from BonRetour c where c.enStock =:stock and c.fournisseur=:four and c.receptionOrUsine =:recUsi and c.type=:retMaq and c.etat =:etat");
                query.setParameter("recUsi",recUsi);
                query.setParameter("stock",stock);
                query.setParameter("four",four);
                query.setParameter("retMaq",retMaq);
                query.setParameter("etat",etat);
                
                return query.list();
     }
                   
                   public List<BonRetour> findBonRetourByFourAndRecAndEtat(String recUsi, String four, String retMaq, String etat) {
                Query query = session.createQuery("select c from BonRetour c where c.fournisseur=:four and c.receptionOrUsine =:recUsi and c.type=:retMaq and c.etat =:etat");
                query.setParameter("recUsi",recUsi);
                query.setParameter("four",four);
                query.setParameter("retMaq",retMaq);
                query.setParameter("etat",etat);
                
                return query.list();
     }
                   
}
