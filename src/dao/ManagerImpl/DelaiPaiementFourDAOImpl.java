package dao.ManagerImpl;

import dao.Entity.DelaiPaiementFour;
import dao.Manager.DelaiPaiementFourDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import java.util.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class DelaiPaiementFourDAOImpl implements DelaiPaiementFourDAO {
     Session session=HibernateUtil.openSession();
  
 
     
        public DelaiPaiementFour findById(int id) {
		return (DelaiPaiementFour)session.get(DelaiPaiementFour.class, id);
		}

	public void add(DelaiPaiementFour delaiPaiementFour) {
            
		session.beginTransaction();
		session.save(delaiPaiementFour);
		
		session.getTransaction().commit();
		//return p;
	}


	public DelaiPaiementFour edit(DelaiPaiementFour e) {
		
                session.beginTransaction();
                DelaiPaiementFour p= (DelaiPaiementFour)session.merge(e);
                session.getTransaction().commit();
	
	return p;
	}


	public void delete(DelaiPaiementFour e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DelaiPaiementFour> findAll() {
              return session.createQuery("select c from DelaiPaiementFour c where c.etat = 'Lancé'").list();
    }

      public DelaiPaiementFour findByFour(String four) {
               
         Query query= session.createQuery("select c from DelaiPaiementFour c where c.fournisseur.nom =:four and c.etat='Lancé' ");
         query.setParameter("four", four);
            return (DelaiPaiementFour)query.uniqueResult();
    }
    
}
