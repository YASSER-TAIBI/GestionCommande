package dao.ManagerImpl;

import dao.Entity.RemiseFinPeriode;
import dao.Manager.RemiseFinPeriodeDAO;
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
public class RemiseFinPeriodeDAOImpl implements RemiseFinPeriodeDAO {
     Session session=HibernateUtil.openSession();

     public RemiseFinPeriode findById(int id) {
		return (RemiseFinPeriode)session.get(RemiseFinPeriode.class, id);
		}

 
    
	public void add(RemiseFinPeriode RemiseFinPeriode) {
		session.beginTransaction();
		session.save(RemiseFinPeriode);
		
		session.getTransaction().commit();
		//return p;
	}


	public RemiseFinPeriode edit(RemiseFinPeriode e) {
		
	session.beginTransaction();
	RemiseFinPeriode p= (RemiseFinPeriode)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(RemiseFinPeriode e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<RemiseFinPeriode> findAll() {
        
              return session.createQuery("select c from RemiseFinPeriode c").list();
              
    }

        public RemiseFinPeriode findByAnnee(String annee) {
		
		Query query = session.createQuery("select c from RemiseFinPeriode c where c.annee =:annee");
		query.setParameter("annee",annee);
                
                return (RemiseFinPeriode) query.uniqueResult();
    }
    
}
