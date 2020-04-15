/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.CommandeRegion;
import dao.Manager.CommandeRegionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

import dao.Entity.DetailCommande;

/**
 *
 * @author h
 */
public class CommandeRegionDAOImpl implements CommandeRegionDAO{
    Session session=HibernateUtil.openSession();


        public List<CommandeRegion>  findCommandeByEtat(String etat) {
		
		Query query = session.createQuery("select u from CommandeRegion u where  u.etat=:etat ");
		query.setParameter("etat", etat);
		
	return  query.list();
	      

				}
    

    
	public CommandeRegion findById(int id) {
		return (CommandeRegion)session.get(CommandeRegion.class, id);
		}

   
	public void add(CommandeRegion commandeRegion) {
		session.beginTransaction();
		session.save(commandeRegion);
		
		session.getTransaction().commit();
		//return p;
	}


	public CommandeRegion edit(CommandeRegion e) {
		
	session.beginTransaction();
	CommandeRegion p= (CommandeRegion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(CommandeRegion e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
        public List<CommandeRegion> findAll(String typeCommandeRegion) {
              Query query = session.createQuery("select c from CommandeRegion c where c.typeCommande=:typeCommande");
                query.setParameter("typeCommande", typeCommandeRegion);
		
	return  query.list();
    }


          

	
}
