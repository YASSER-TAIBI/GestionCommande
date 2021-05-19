/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.TypeCarton;
import dao.Manager.TypeCartonDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class TypeCartonDAOImpl implements TypeCartonDAO   {
    Session session=HibernateUtil.openSession();




	public TypeCarton findById(int id) {
		return (TypeCarton)session.get(TypeCarton.class, id);
		}

	public void add(TypeCarton intervalle) {
		session.beginTransaction();
		session.save(intervalle);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public TypeCarton edit(TypeCarton e) {
		
	session.beginTransaction();
	TypeCarton p= (TypeCarton)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(TypeCarton e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<TypeCarton> findAll() {
		return session.createQuery("select c from TypeCarton c where c.etat = 'Lancé'").list();
		}

	 public List<TypeCarton> findByCodeTypeCarton(String code) {
		
		Query query = session.createQuery("select u from TypeCarton u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<TypeCarton> findBylibelleTypeCarton(String lib) {
		
		Query query = session.createQuery("select u from TypeCarton u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }

    
}
