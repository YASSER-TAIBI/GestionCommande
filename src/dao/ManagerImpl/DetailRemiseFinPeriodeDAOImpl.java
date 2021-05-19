package dao.ManagerImpl;

import dao.Entity.DetailRemiseFinPeriode;
import dao.Manager.DetailRemiseFinPeriodeDAO;
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
public class DetailRemiseFinPeriodeDAOImpl implements DetailRemiseFinPeriodeDAO {
     Session session=HibernateUtil.openSession();

     public DetailRemiseFinPeriode findById(int id) {
		return (DetailRemiseFinPeriode)session.get(DetailRemiseFinPeriode.class, id);
		}

 
    
	public void add(DetailRemiseFinPeriode detailRemiseFinPeriode) {
		session.beginTransaction();
		session.save(detailRemiseFinPeriode);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailRemiseFinPeriode edit(DetailRemiseFinPeriode e) {
		
	session.beginTransaction();
	DetailRemiseFinPeriode p= (DetailRemiseFinPeriode)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailRemiseFinPeriode e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailRemiseFinPeriode> findAll() {
              return session.createQuery("select c from DetailRemiseFinPeriode c").list();
    }

    
            public List<DetailRemiseFinPeriode> findByRemiseFinPeriode(int rfp) {
		
		Query query = session.createQuery("select c from DetailRemiseFinPeriode c where c.remiseFinPeriode.id =:rfp");
		query.setParameter("rfp",rfp);
                
                	return query.list();
    }
}
