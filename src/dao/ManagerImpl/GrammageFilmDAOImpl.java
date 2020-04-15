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
		return session.createQuery("select c from GrammageFilm c").list();
		}

    
}
