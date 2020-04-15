/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailCompte;
import dao.Manager.DetailCompteDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailCompteDAOImpl implements DetailCompteDAO {
     Session session=HibernateUtil.openSession();
  
     
     
     
     public DetailCompte findById(int id) {
		return (DetailCompte)session.get(DetailCompte.class, id);
		}

 
    
	public void add(DetailCompte detailCompte) {
		session.beginTransaction();
		session.save(detailCompte);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailCompte edit(DetailCompte e) {
		
	session.beginTransaction();
	DetailCompte p= (DetailCompte)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCompte e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailCompte> findAll() {
              return session.createQuery("select c from DetailCompte c").list();
    }

       public List<DetailCompte> findFourByRechercheNom(String four) {
		
		Query query = session.createQuery("select c from DetailCompte c where fournisseur =:four");
		query.setParameter("four",four);
               
		
                return query.list();
 }
    
         public List<DetailCompte> findDetailCompteByCompte(int idCompte) {
		
		Query query = session.createQuery("select c from DetailCompte c where c.compteFourMP.id =:idCompte");
		query.setParameter("idCompte",idCompte);
               
		
                return query.list();
 }
    
          public List<DetailCompte> findFourByRechercheNomReglement(int four,int clt) {
		
		Query query = session.createQuery("select c from DetailCompte c where c.clientMP.id =:clt and c.compteFourMP.id =:four" );
		query.setParameter("four",four);
                query.setParameter("clt",clt);
           
           
		
                return query.list();
 }
           public List<DetailCompte> findClientByDetailCompte(String report, int clt, int idCompte) {
		
		Query query = session.createQuery("select c from DetailCompte c where c.clientMP.id=:clt and c.designation like :report and c.compteFourMP.id=:idCompte" );
		query.setParameter("report","%"+report+"%");
                query.setParameter("clt",clt);
                query.setParameter("idCompte",idCompte);
           
           
		
                return query.list();
          
          
           }
             public  DetailCompte findByDetailCompte(String recpBL) {
		
		Query query = session.createQuery("select c from DetailCompte c where c.designation =:recpBL" );
		query.setParameter("recpBL",recpBL);
            
           
           
		 return (DetailCompte)  query.uniqueResult();
	      
				}
             
         
          
             public List<DetailCompte> findFilterDetailCompteByDateOperationAndFour(Date dateDebut,Date dateFin, int four) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCompte c where c.dateBonLivraison BETWEEN :dateDebut and :dateFin and c.compteFourMP.id =:four");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("four", four);
         }
           return query.list();
    }
                public List<DetailCompte> findFilterDetailCompteByDateOperationAndFourAndClient(Date dateDebut,Date dateFin, int four, int client) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCompte c where c.dateBonLivraison BETWEEN :dateDebut and :dateFin and c.compteFourMP.id =:four and c.clientMP.id =:client ");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("four", four);
         query.setParameter("client", client);
         
         }
           return query.list();
    }
                
                         public List<Object[]> findBy(Date dateDebut,int client, int comptFour) {
        Query query=  session.createQuery("select sum(c.montantDebit),sum(c.montantCredit)  from DetailCompte c where c.clientMP.id =:client and c.compteFourMP.id =:comptFour and c.dateBonLivraison <:dateDebut");
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("client", client);
        query.setParameter("comptFour", comptFour);
        return query.list();
    }
}
