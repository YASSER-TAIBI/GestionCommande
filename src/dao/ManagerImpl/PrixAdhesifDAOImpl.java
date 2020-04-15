/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixAdhesif;
import dao.Manager.PrixAdhesifDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixAdhesifDAOImpl implements PrixAdhesifDAO   {
    Session session=HibernateUtil.openSession();




	public PrixAdhesif findById(int id) {
		return (PrixAdhesif)session.get(PrixAdhesif.class, id);
		}

	public void add(PrixAdhesif prixAdhesif) {
		session.beginTransaction();
		session.save(prixAdhesif);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixAdhesif edit(PrixAdhesif e) {
		
	session.beginTransaction();
	PrixAdhesif p= (PrixAdhesif)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixAdhesif e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixAdhesif> findAll() {
		return session.createQuery("select c from PrixAdhesif c").list();
		}
        
           public PrixAdhesif findDimensionByPrix(int idDim, int idFour, int idCg) {
		
		Query query = session.createQuery("select u from PrixAdhesif u where u.categorieMp.id=:idCg and u.fournisseur.id=:idFour and u.dimension.id=:idDim ");
		query.setParameter("idDim", idDim);
                query.setParameter("idFour", idFour);
                query.setParameter("idCg", idCg);
                
		

                return (PrixAdhesif)  query.uniqueResult();
        
				}
           
              public PrixAdhesif findPrixAdhesifByRegion(int idDim, int idCg) {
		
		Query query = session.createQuery("select u from PrixAdhesif u where u.categorieMp.id=:idCg and u.dimension.id=:idDim ");
		query.setParameter("idDim", idDim);
                query.setParameter("idCg", idCg);

                return (PrixAdhesif)  query.uniqueResult();
        
				}
           
               public List<PrixAdhesif> findPrixAdhesifByFour(int idFour) {
		
		Query query = session.createQuery("select c from PrixAdhesif c where c.fournisseur.id=:idFour");
		query.setParameter("idFour",idFour);
               
		
                return query.list();
 }
                      public List<PrixAdhesif>  findPrixAdhesifByCategorieMp(int CatMp, int Four) {
		
		Query query = session.createQuery("select u from PrixAdhesif u where  u.categorieMp.id=:CatMp and u.fournisseur.id =:Four");
		query.setParameter("CatMp", CatMp);
                query.setParameter("Four", Four);
		
	return  query.list();
	      
    }
                      
                      public List<PrixAdhesif>  findPrixAdhesifByCategorieMpTMP(int CatMp) {
		
		Query query = session.createQuery("select u from PrixAdhesif u where  u.categorieMp.id=:CatMp ");
		query.setParameter("CatMp", CatMp);
     
		
	return  query.list();
	      
    }
}
