/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Commande;
import dao.Entity.TypeCartonBox;
import dao.Manager.TypeCartonBoxDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class TypeCartonBoxDAOImpl implements TypeCartonBoxDAO   {
    Session session=HibernateUtil.openSession();




	public TypeCartonBox findById(int id) {
		return (TypeCartonBox)session.get(TypeCartonBox.class, id);
		}

	public void add(TypeCartonBox intervalle) {
		session.beginTransaction();
		session.save(intervalle);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public TypeCartonBox edit(TypeCartonBox e) {
		
	session.beginTransaction();
	TypeCartonBox p= (TypeCartonBox)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(TypeCartonBox e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<TypeCartonBox> findAll() {
		return session.createQuery("select c from TypeCartonBox c where c.etat = 'Lancé'").list();
		}

	 public List<TypeCartonBox> findByCodeCartBox(String code) {
		
		Query query = session.createQuery("select u from TypeCartonBox u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<TypeCartonBox> findBylibelleCartBox(String lib) {
		
		Query query = session.createQuery("select u from TypeCartonBox u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }
         
}
