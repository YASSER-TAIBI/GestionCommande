/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixCarton;
import dao.Manager.PrixCartonDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixCartonDAOImpl implements PrixCartonDAO   {
    Session session=HibernateUtil.openSession();




	public PrixCarton findById(int id) {
		return (PrixCarton)session.get(PrixCarton.class, id);
		}

	public void add(PrixCarton prixCarton) {
		session.beginTransaction();
		session.save(prixCarton);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixCarton edit(PrixCarton e) {
		
	session.beginTransaction();
	PrixCarton p= (PrixCarton)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixCarton e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixCarton> findAll() {
		return session.createQuery("select c from PrixCarton c").list();
		}
        

		

           
               public List<PrixCarton> findPrixCartonByFour(int idFour) {
		
		Query query = session.createQuery("select c from PrixCarton c where c.fournisseur.id=:idFour");
		query.setParameter("idFour",idFour);
               
		
                return query.list();
 }
               
    

                public PrixCarton findDimensionByPrixCarton(int idDim, int idFour, int idCg, int idTcb,int idInt) {
		
		Query query = session.createQuery("select u from PrixCarton u where u.categorieMp.id=:idCg and u.fournisseur.id=:idFour and u.dimension.id=:idDim and u.typeCarton.id=:idTcb and u.intervalle.id  =:idInt");
		query.setParameter("idDim", idDim);
                query.setParameter("idFour", idFour);
                query.setParameter("idCg", idCg);
                query.setParameter("idTcb", idTcb);
                query.setParameter("idInt", idInt);

		
	return (PrixCarton)  query.uniqueResult();
        
				}
                
                
                   public PrixCarton findPrixCartonByRegion(int idDim, int idCg, int idTcb,int idInt) {
		
		Query query = session.createQuery("select u from PrixCarton u where u.categorieMp.id=:idCg and u.dimension.id=:idDim and u.typeCarton.id=:idTcb and u.intervalle.id=:idInt");
		query.setParameter("idDim", idDim);
                query.setParameter("idCg", idCg);
                query.setParameter("idTcb", idTcb);
                query.setParameter("idInt", idInt);

		
	return (PrixCarton)  query.uniqueResult();
        
				}
                
                    public List<PrixCarton>  findPrixCartonByCategorieMp(int CatMp, int Four) {
		
		Query query = session.createQuery("select u from PrixCarton u where  u.categorieMp.id=:CatMp and u.fournisseur.id =:Four");
		query.setParameter("CatMp", CatMp);
                query.setParameter("Four", Four);
		
	return  query.list();
	      
    }
               
                                      public List<PrixCarton>  findPrixCartonByCategorieMpTMP(int CatMp) {
		
		Query query = session.createQuery("select u from PrixCarton u where  u.categorieMp.id=:CatMp ");
		query.setParameter("CatMp", CatMp);
		
	return  query.list();
	      
    }
 
                    
}