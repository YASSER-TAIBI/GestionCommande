/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Ville;
import dao.Manager.VilleDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author Hp
 */
public class VilleDAOImpl implements VilleDAO  {
    Session session=HibernateUtil.openSession();




	public Ville findById(int id) {
		return (Ville)session.get(Ville.class, id);
		}

	public void add(Ville utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public Ville edit(Ville e) {
		
	session.beginTransaction();
	Ville p= (Ville)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Ville e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Ville> findAll() {
		return session.createQuery("select c from Ville c where c.etat = 'Lancé'").list();
		}

    @Override
    public List<Ville> findVilleByRechercheLibelle(String libelle) {
     Query query = session.createQuery("select u from Ville u where libelle u.like :libelle and u.etat = 'Lancé'");
		query.setParameter("libelle","%"+libelle+"%");
               
		
                return query.list(); 
    }

    @Override
    public List<Ville> findVilleByRechercheCode(String code) {
          Query query = session.createQuery("select u from Ville u where u.code like :code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
               
		
                return query.list(); 
    }
	

		 public List<Ville> findByCodeVille(String code) {
		
		Query query = session.createQuery("select u from Ville u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<Ville> findBylibelleVille(String lib) {
		
		Query query = session.createQuery("select u from Ville u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }


}
