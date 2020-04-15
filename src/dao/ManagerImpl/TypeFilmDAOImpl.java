/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.TypeFilm;
import dao.Manager.TypeFilmDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class TypeFilmDAOImpl  implements TypeFilmDAO   {
    Session session=HibernateUtil.openSession();




	public TypeFilm findById(int id) {
		return (TypeFilm)session.get(TypeFilm.class, id);
		}

	public void add(TypeFilm intervalle) {
		session.beginTransaction();
		session.save(intervalle);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public TypeFilm edit(TypeFilm e) {
		
	session.beginTransaction();
	TypeFilm p= (TypeFilm)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(TypeFilm e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<TypeFilm> findAll() {
		return session.createQuery("select c from TypeFilm c").list();
		}


    
}
