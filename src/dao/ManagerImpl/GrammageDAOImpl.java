/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Grammage;
import dao.Manager.GrammageDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class GrammageDAOImpl implements GrammageDAO  {
    Session session=HibernateUtil.openSession();




	public Grammage findById(int id) {
		return (Grammage)session.get(Grammage.class, id);
		}

	public void add(Grammage grammage) {
		session.beginTransaction();
		session.save(grammage);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public Grammage edit(Grammage e) {
		
	session.beginTransaction();
	Grammage p= (Grammage)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Grammage e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Grammage> findAll() {
		return session.createQuery("select c from Grammage c where c.etat = 'Lancé'").list();
		}

    	 public List<Grammage> findByCodeGrammage(String code) {
		
		Query query = session.createQuery("select u from Grammage u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<Grammage> findBylibelleGrammage(String lib) {
		
		Query query = session.createQuery("select u from Grammage u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }
}
