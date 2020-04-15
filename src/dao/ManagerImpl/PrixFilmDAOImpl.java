/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixFilm;
import dao.Manager.PrixFilmDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixFilmDAOImpl implements PrixFilmDAO   {
    Session session=HibernateUtil.openSession();




	public PrixFilm findById(int id) {
		return (PrixFilm)session.get(PrixFilm.class, id);
		}

	public void add(PrixFilm prixFilm) {
		session.beginTransaction();
		session.save(prixFilm);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixFilm edit(PrixFilm e) {
		
	session.beginTransaction();
	PrixFilm p= (PrixFilm)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixFilm e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixFilm> findAll() {
		return session.createQuery("select c from PrixFilm c").list();
		}

        public List<PrixFilm> findPrixFilmByFour(int idFour) {
		
		Query query = session.createQuery("select c from PrixFilm c where c.fournisseur.id=:idFour");
		query.setParameter("idFour",idFour);
               
		
                return query.list();
 }

         
         
                public PrixFilm findDimensionByPrixFilm(int idFour, int idTfilm, int idGfilm,int idCg,int idInt) {
		
		Query query = session.createQuery("select u from PrixFilm u where u.categorieMp.id=:idCg and u.fournisseur.id=:idFour and u.typeFilm.id=:idTfilm and u.grammageFilm.id =:idGfilm and u.intervalle.id=:idInt");
                query.setParameter("idFour", idFour);
                query.setParameter("idTfilm", idTfilm);
                query.setParameter("idGfilm", idGfilm);
                query.setParameter("idCg", idCg);
                query.setParameter("idInt", idInt);
		
	return (PrixFilm)  query.uniqueResult();

        
				}
                
                   public PrixFilm findPrixFilmByRegion(int idTfilm, int idGfilm,int idCg,int idInt) {
		
		Query query = session.createQuery("select u from PrixFilm u where u.categorieMp.id=:idCg and u.typeFilm.id=:idTfilm and u.grammageFilm.id =:idGfilm and u.intervalle.id=:idInt");
		query.setParameter("idTfilm", idTfilm);
                query.setParameter("idGfilm", idGfilm);
                query.setParameter("idCg", idCg);
                query.setParameter("idInt", idInt);

		
	return (PrixFilm)  query.uniqueResult();
        
				}
                
                     public List<PrixFilm>  findPrixFilmByCategorieMp(int CatMp , int Four) {
		
		Query query = session.createQuery("select u from PrixFilm u where  u.categorieMp.id=:CatMp  and u.fournisseur.id =:Four");
		query.setParameter("CatMp", CatMp);
		query.setParameter("Four", Four);
                
	return  query.list();
	      
    }
        
                       public List<PrixFilm>  findPrixFilmByCategorieMpTMP(int CatMp) {
		
		Query query = session.createQuery("select u from PrixFilm u where  u.categorieMp.id=:CatMp ");
		query.setParameter("CatMp", CatMp);

                
	return  query.list();
	      
    }
     
}
