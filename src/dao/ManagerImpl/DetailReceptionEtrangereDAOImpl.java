/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailReceptionEtrangere;
import dao.Manager.DetailReceptionEtrangereDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author h
 */
public class DetailReceptionEtrangereDAOImpl implements DetailReceptionEtrangereDAO {
      Session session=HibernateUtil.openSession();



	public DetailReceptionEtrangere findById(int id) {
		return (DetailReceptionEtrangere)session.get(DetailReceptionEtrangere.class, id);
		}

	public void add(DetailReceptionEtrangere detailReceptionEtrangere) {
		session.beginTransaction();
		session.save(detailReceptionEtrangere);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailReceptionEtrangere edit(DetailReceptionEtrangere e) {
		
	session.beginTransaction();
	DetailReceptionEtrangere p= (DetailReceptionEtrangere)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailReceptionEtrangere e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailReceptionEtrangere> findAll() {
		return session.createQuery("select c from DetailReceptionEtrangere c").list();
		}



  

}
