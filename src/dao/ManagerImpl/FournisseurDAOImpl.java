/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Fournisseur;
import dao.Manager.FournisseurDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author h
 */
public class FournisseurDAOImpl implements FournisseurDAO {
    Session session=HibernateUtil.openSession();


	public Fournisseur findById(int id) {
		return (Fournisseur)session.get(Fournisseur.class, id);
		}


	public void add(Fournisseur fournisseur) {
		session.beginTransaction();
		session.save(fournisseur);
		
		session.getTransaction().commit();
		//return p;
	}


	public Fournisseur edit(Fournisseur e) {
		
	session.beginTransaction();
	Fournisseur p= (Fournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Fournisseur e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<Fournisseur> findAll() {
		return session.createQuery("select c from Fournisseur c").list();
		}

    @Override
    public int getMaxId() {
          int id;
        Query query= session.createQuery("select Max(id) from Fournisseur c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;

       return id;
    }
	
    public List<Fournisseur> findFourByRechercheNom(String nom) {
		
		Query query = session.createQuery("select u from Fournisseur u where nom like :nom");
		query.setParameter("nom","%"+nom+"%");
               
		
                return query.list();
 }
 public List<Fournisseur> findFourByRechercheCode(String code) {
		
		Query query = session.createQuery("select u from Fournisseur u where code like :code");
		query.setParameter("code","%"+code+"%");
               
		
                return query.list();
 }
 
 public List<Fournisseur> findFourByRechercheVille(String ville) {
		
		Query query = session.createQuery("select u from Fournisseur u where ville like :ville");
		query.setParameter("ville","%"+ville+"%");
               
		
                return query.list();
 }
  
    public Fournisseur findByFournisseur(String four) {
		
		Query query = session.createQuery("select u from Fournisseur u where u.nom =:four ");
		query.setParameter("four",four);
                
                return (Fournisseur)query.uniqueResult();
 }
 
        public List<Fournisseur> findFourByTypeFour() {
		
		Query query = session.createQuery("select u from Fournisseur u where u.typeFournisseur = 'Etranger'");

                return query.list();
 }
    
}
