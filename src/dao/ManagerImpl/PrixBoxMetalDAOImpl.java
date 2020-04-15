/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBoxMetal;
import dao.Manager.PrixBoxMetalDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixBoxMetalDAOImpl implements PrixBoxMetalDAO   {
    Session session=HibernateUtil.openSession();




	public PrixBoxMetal findById(int id) {
		return (PrixBoxMetal)session.get(PrixBoxMetal.class, id);
		}

	public void add(PrixBoxMetal prixBoxMetal) {
		session.beginTransaction();
		session.save(prixBoxMetal);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixBoxMetal edit(PrixBoxMetal e) {
		
	session.beginTransaction();
	PrixBoxMetal p= (PrixBoxMetal)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixBoxMetal e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixBoxMetal> findAll() {
		return session.createQuery("select c from PrixBoxMetal c").list();
		}
        
           public PrixBoxMetal findDimensionByPrixBoxMetal( int idFour, int idCg) {
		
		Query query = session.createQuery("select u from PrixBoxMetal u where u.categorieMp.id=:idCg and u.fournisseur.id=:idFour ");
                query.setParameter("idFour", idFour);
                query.setParameter("idCg", idCg);
                
		

                return (PrixBoxMetal)  query.uniqueResult();
        
				}
//           
//              public PrixAdhesif findPrixAdhesifByRegion(int idDim, int idCg) {
//		
//		Query query = session.createQuery("select u from PrixAdhesif u where u.categorieMp.id=:idCg and u.dimension.id=:idDim ");
//		query.setParameter("idDim", idDim);
//                query.setParameter("idCg", idCg);
//
//                return (PrixAdhesif)  query.uniqueResult();
//        
//				}
//           
               public List<PrixBoxMetal> findPrixBoxMetalByFour(int idFour) {
		
		Query query = session.createQuery("select c from PrixBoxMetal c where c.fournisseur.id=:idFour");
		query.setParameter("idFour",idFour);
               
		
                return query.list();
 }
//                      public List<PrixAdhesif>  findPrixAdhesifByCategorieMp(int CatMp, int Four) {
//		
//		Query query = session.createQuery("select u from PrixAdhesif u where  u.categorieMp.id=:CatMp and u.fournisseur.id =:Four");
//		query.setParameter("CatMp", CatMp);
//                query.setParameter("Four", Four);
//		
//	return  query.list();
//	      
//    }
//                      
//                      public List<PrixAdhesif>  findPrixAdhesifByCategorieMpTMP(int CatMp) {
//		
//		Query query = session.createQuery("select u from PrixAdhesif u where  u.categorieMp.id=:CatMp ");
//		query.setParameter("CatMp", CatMp);
//     
//		
//	return  query.list();
//	      
//    }
}
