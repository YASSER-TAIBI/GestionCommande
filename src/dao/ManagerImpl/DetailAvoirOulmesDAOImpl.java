/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailAvoirOulmes;
import dao.Manager.DetailAvoirOulmesDAO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailAvoirOulmesDAOImpl implements DetailAvoirOulmesDAO {
    Session session=HibernateUtil.openSession();


	public DetailAvoirOulmes findById(int id) {
		return (DetailAvoirOulmes)session.get(DetailAvoirOulmes.class, id);
		}

	public void add(DetailAvoirOulmes detailAvoirOulmes) {
		session.beginTransaction();
		session.save(detailAvoirOulmes);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailAvoirOulmes edit(DetailAvoirOulmes e) {
		
	session.beginTransaction();
	DetailAvoirOulmes p= (DetailAvoirOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailAvoirOulmes e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailAvoirOulmes> findAll() {
		return session.createQuery("select c from DetailAvoirOulmes c").list();
		}
        
        
        public List<DetailAvoirOulmes>  findByAvoirOulmes(int idAvoirOulmes) {
		
		Query query = session.createQuery("select u from DetailAvoirOulmes u where u.avoirOulmes.id=:idAvoirOulmes");
                query.setParameter("idAvoirOulmes", idAvoirOulmes);
                
	return  query.list();
				} 
        
         public List<DetailAvoirOulmes> findByEtat(String etat) {
		
		Query query = session.createQuery("select u from DetailAvoirOulmes u where u.avoirOulmes.etat=:etat");
                query.setParameter("etat", etat);
                
	return  query.list();
				} 
         
         
                      public List<DetailAvoirOulmes> findByAllFilter(String etat, String req) {
		
		Query query = session.createQuery("select u from DetailAvoirOulmes u where u.avoirOulmes.etat=:etat"+req);
		query.setParameter("etat", etat);
                
	return  query.list();
				}
         
                      public List<Object[]> findBySitiationGlobalAvoir() {
        Query query=  session.createQuery("select c.prixOulmes.produit, COALESCE(sum(c.qte),0) , COALESCE(sum(c.qteFactureAvoir),0) , COALESCE(sum(c.qte),0)- COALESCE(sum(c.qteFactureAvoir),0) from DetailAvoirOulmes c where c.prixOulmes.produit.palette = 0 group by c.prixOulmes.produit");
        return query.list();
}
              
                      public List<Object[]> findBySitiationGlobalAvoirWithCodeArt(String code) {
        Query query=  session.createQuery("select c.prixOulmes.produit, COALESCE(sum(c.qte),0) , COALESCE(sum(c.qteFactureAvoir),0) , COALESCE(sum(c.qte),0)- COALESCE(sum(c.qteFactureAvoir),0) from DetailAvoirOulmes c where c.prixOulmes.produit.code =:code and c.prixOulmes.produit.palette = 0 group by c.prixOulmes.produit");
        query.setParameter("code", code);
        
        return query.list();
}
                      
              public List<DetailAvoirOulmes> findByPrixOulmes(String libelle) {
		
		Query query = session.createQuery("select u from DetailAvoirOulmes u where u.prixOulmes.produit.libelle=:libelle");
                query.setParameter("libelle", libelle);
                
	return  query.list();
				}             
                      
                       public List<Object[]> findByCodeAndClient(String code, String client, String req) {
        Query query=  session.createQuery("select u.avoirOulmes.dateSaisie, u.avoirOulmes.designation , u.avoirOulmes.numFacture ,u.qte, u.qteFactureAvoir from DetailAvoirOulmes u where u.prixOulmes.produit.code =:code and u.avoirOulmes.client =:client"+req);
        query.setParameter("code", code);
        query.setParameter("client", client);
        
        return query.list();
    }
                       
    
            public List<DetailAvoirOulmes> findByPrixOulmesWithCodeArt(String code, String req) {
		
		Query query = session.createQuery("select u from DetailAvoirOulmes u where u.prixOulmes.produit.code=:code"+req);
                query.setParameter("code", code);
                
	return  query.list();
				}
                        
           public List<Object[]> findByAvoirEmballage() {
        Query query=  session.createQuery("select c.prixOulmes,sum(c.qte) from DetailAvoirOulmes c where YEAR(c.avoirOulmes.dateSaisie) = '2021' and c.prixOulmes in(28,29,21) group by c.prixOulmes");

        return query.list();
    }   
           public List<Object[]> findByAvoirEmballageAndClientAndFour(String client, String four) {
        Query query=  session.createQuery("select c.prixOulmes,sum(c.qte) from DetailAvoirOulmes c where YEAR(c.avoirOulmes.dateSaisie) = '2021' and c.prixOulmes in(28,29,21) and c.avoirOulmes.client =:client and c.avoirOulmes.fournisseur =:four group by c.prixOulmes");
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }  
        
           
                      public List<Object[]> findByAvoirEmballageAndMois(int prixO) {
        Query query=  session.createQuery("select MONTH(d.dateSaisie) , c.prixOulmes , sum(c.qte) from DetailAvoirOulmes c , AvoirOulmes d where c.avoirOulmes = d.id and c.prixOulmes.id =:prixO and YEAR(d.dateSaisie) = '2021' group by MONTH(d.dateSaisie)");
        query.setParameter("prixO", prixO);
        return query.list();
    }   
             public List<Object[]> findByAvoirEmballageAndMoisAndClientAndFour(int prixO, String client, String four) {
        Query query=  session.createQuery("select MONTH(d.dateSaisie) ,c.prixOulmes , sum(c.qte) from DetailAvoirOulmes c , AvoirOulmes d where c.avoirOulmes = d.id and c.prixOulmes.id =:prixO and YEAR(d.dateSaisie) = '2021' and d.client =:client and d.fournisseur =:four group by MONTH(d.dateSaisie)");
        query.setParameter("prixO", prixO);
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }               
             
             
                             public BigDecimal findByLivGMS(int mois) {
		Query query = session.createQuery("select SUM(u.montant) from DetailAvoirOulmes u where u.avoirOulmes.client2 <> 'Sans' and YEAR(u.avoirOulmes.dateSaisie) ='2021' and MONTH(u.avoirOulmes.dateSaisie)=:mois");
		query.setParameter("mois",mois);
		
                return (BigDecimal)query.uniqueResult();
 }
}