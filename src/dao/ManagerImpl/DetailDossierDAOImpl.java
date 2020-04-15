/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailCompte;
import dao.Entity.DetailDossier;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailDossierDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailDossierDAOImpl  implements DetailDossierDAO {
       Session session=HibernateUtil.openSession();
  
     
     
     
     public DetailDossier findById(int id) {
		return (DetailDossier)session.get(DetailDossier.class, id);
		}

 
    
	public void add(DetailDossier detailDossier) {
		session.beginTransaction();
		session.save(detailDossier);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailDossier edit(DetailDossier e) {
		
	session.beginTransaction();
	DetailDossier p= (DetailDossier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailDossier e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailDossier> findAll() {
              return session.createQuery("select c from DetailDossier c").list();
    }


  
}
