/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.AvanceFournisseur;
import dao.Manager.AvanceFournisseurDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import java.util.Date;

/**
 *
 * @author h
 */
public class AvanceFournisseurDAOImpl implements AvanceFournisseurDAO{
    Session session=HibernateUtil.openSession();


    
	public AvanceFournisseur findById(int id) {
		return (AvanceFournisseur)session.get(AvanceFournisseur.class, id);
		}

   
	public void add(AvanceFournisseur avanceFournisseur) {
		session.beginTransaction();
		session.save(avanceFournisseur);
		
		session.getTransaction().commit();
		//return p;
	}


	public AvanceFournisseur edit(AvanceFournisseur e) {
		
	session.beginTransaction();
	AvanceFournisseur p= (AvanceFournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(AvanceFournisseur e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<AvanceFournisseur> findAll() {
              Query query = session.createQuery("select c from AvanceFournisseur c ");
	return  query.list();
    }

    public List<AvanceFournisseur> findByClientAndFour(int client,int four) {
		
		Query query = session.createQuery("select c from AvanceFournisseur c where c.clientMP.id =:client and c.fourisseur.id =:four" );
		query.setParameter("client",client);
                query.setParameter("four",four);
                
                return query.list();
                
 }
	
}
