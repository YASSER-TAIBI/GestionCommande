/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailCommande;
import dao.Manager.DetailCommandeDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Commande;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author h
 */
public class DetailCommandeDAOImpl implements DetailCommandeDAO {
    Session session=HibernateUtil.openSession();

	public DetailCommande findById(int id) {
		return (DetailCommande)session.get(DetailCommande.class, id);
		}

	public void add(DetailCommande utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailCommande edit(DetailCommande e) {
		
	session.beginTransaction();
	DetailCommande p= (DetailCommande)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCommande e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		      
	}

        
	
      public List<DetailCommande> findByEtat(String etat) {
		Query query = session.createQuery("select c from DetailCommande c where c.etat=:etat");
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }


                  public List<DetailCommande> findDetailCommandeByEtat(Commande commande, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.commande =:commande and u.etat=:etat");
		query.setParameter("commande",commande);
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }
                      
             public List<DetailCommande> findDetailNumCommandeByEtat(String Numcom, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.commande.nCommande =:Numcom and u.etat=:etat");
		query.setParameter("Numcom",Numcom);
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }
                         
               
          public List<DetailCommande> findDetailCommandeByMpAndEtat(int mp, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.quantiteRestee <> 0 and  u.matierePremier.id =:mp and u.etat =:etat ");
		query.setParameter("mp", mp);
                query.setParameter("etat", etat);
	    return query.list();
	      
				}    
             
          public DetailCommande findDetailCommandeByBonRetour(String Mp ,String idFour,String etat,String nCom) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.commande.fourisseur.nom =:idFour and c.etat =:etat and c.commande.nCommande =:nCom");
		query.setParameter("Mp",Mp);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
		query.setParameter("nCom", nCom);

                return (DetailCommande)  query.uniqueResult();
                         }
            
                        
           public List<DetailCommande>  findBySituationGlobalCommandeMp (String etat, String req) {

		Query query= session.createQuery("select c from DetailCommande c where c.commande.typeCommande ='Interne' and c.etat =:etat"+req );
		query.setParameter("etat", etat);
                
		return query.list();
}
          
           public List<DetailCommande>  findBySituationGlobalCommandePf (String etat, String req) {

		Query query= session.createQuery("select c from DetailCommande c where c.commande.typeCommande ='Interne Art' and c.etat =:etat"+req );
		query.setParameter("etat", etat);
                
		return query.list();
}
}

