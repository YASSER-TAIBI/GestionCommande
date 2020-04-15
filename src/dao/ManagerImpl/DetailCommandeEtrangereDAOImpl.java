/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommandeEtrangere;
import dao.Manager.CommandeEtrangereDAO;
import dao.Manager.DetailCommandeEtrangereDAO;

/**
 *
 * @author h
 */
public class DetailCommandeEtrangereDAOImpl implements DetailCommandeEtrangereDAO {
    Session session=HibernateUtil.openSession();

	

	public DetailCommandeEtrangere findById(int id) {
		return (DetailCommandeEtrangere)session.get(DetailCommandeEtrangere.class, id);
		}


	public void add(DetailCommandeEtrangere detailCommandeEtrangere) {
            
		session.beginTransaction();
		session.save(detailCommandeEtrangere);
		session.getTransaction().commit();
		//return p;
	}


	public DetailCommandeEtrangere edit(DetailCommandeEtrangere e) {
		
	session.beginTransaction();
	DetailCommandeEtrangere p= (DetailCommandeEtrangere)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCommandeEtrangere e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<DetailCommandeEtrangere> findAll() {
		return session.createQuery("select c from DetailCommandeEtrangere c").list();
		}
      
        public List<DetailCommandeEtrangere> findDetailCommandeEtrangereByCommandeEtr (CommandeEtrangere comEtr ) {
		Query query = session.createQuery("select c from DetailCommandeEtrangere c where c.commandeEtrangere =:comEtr )");
                query.setParameter("comEtr",comEtr);
		
          return query.list();
        }
        
 
      
    
}
