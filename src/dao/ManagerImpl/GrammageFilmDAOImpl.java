/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.GrammageFilm;
import dao.Manager.GrammageFilmDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class GrammageFilmDAOImpl implements GrammageFilmDAO  {
    Session session=HibernateUtil.openSession();




	public GrammageFilm findById(int id) {
		return (GrammageFilm)session.get(GrammageFilm.class, id);
		}

	public void add(GrammageFilm grammageFilm) {
		session.beginTransaction();
		session.save(grammageFilm);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public GrammageFilm edit(GrammageFilm e) {
		
	session.beginTransaction();
	GrammageFilm p= (GrammageFilm)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(GrammageFilm e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<GrammageFilm> findAll() {
		return session.createQuery("select c from GrammageFilm c where c.etat = 'Lancé'").list();
		}

    
        	 public List<GrammageFilm> findByCodeGrammageFilm(String code) {
		
		Query query = session.createQuery("select u from GrammageFilm u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<GrammageFilm> findBylibelleGrammageFilm(String lib) {
		
		Query query = session.createQuery("select u from GrammageFilm u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }

        
}
