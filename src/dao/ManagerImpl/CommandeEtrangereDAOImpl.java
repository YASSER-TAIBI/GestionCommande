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
import dao.Manager.CommandeEtrangereDAO;

/**
 *
 * @author h
 */
public class CommandeEtrangereDAOImpl implements CommandeEtrangereDAO {
    Session session=HibernateUtil.openSession();

	

	public CommandeEtrangere findById(int id) {
		return (CommandeEtrangere)session.get(CommandeEtrangere.class, id);
		}


	public void add(CommandeEtrangere commandeEtrangere) {
            
		session.beginTransaction();
		session.save(commandeEtrangere);
		session.getTransaction().commit();
		//return p;
	}


	public CommandeEtrangere edit(CommandeEtrangere e) {
		
	session.beginTransaction();
	CommandeEtrangere p= (CommandeEtrangere)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(CommandeEtrangere e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<CommandeEtrangere> findAll() {
		return session.createQuery("select c from CommandeEtrangere c").list();
		}

       public List<CommandeEtrangere> findCommandeEtrangereByEtat (String etat ) {
		Query query = session.createQuery("select c from CommandeEtrangere c where c.etat =:etat )");
                query.setParameter("etat",etat);
		
          return query.list();
        }

            public List<CommandeEtrangere> findCommandeEtrangereByNumDossier (String numDossier ) {
		Query query = session.createQuery("select c from CommandeEtrangere c where c.numDoussier =:numDossier )");
                query.setParameter("numDossier",numDossier);
		
          return query.list();
        } 
       
       
    
}
