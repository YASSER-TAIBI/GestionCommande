/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailCommandeRegion;
import dao.Manager.DetailCommandeRegionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Commande;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommande;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author h
 */
public class DetailCommandeRegionDAOImpl implements DetailCommandeRegionDAO {
    Session session=HibernateUtil.openSession();


	

	public DetailCommandeRegion findById(int id) {
		return (DetailCommandeRegion)session.get(DetailCommandeRegion.class, id);
		}

	public void add(DetailCommandeRegion detailCommandeRegion) {
		session.beginTransaction();
		session.save(detailCommandeRegion);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailCommandeRegion edit(DetailCommandeRegion e) {
		
	session.beginTransaction();
	DetailCommandeRegion p= (DetailCommandeRegion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCommandeRegion e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		      
	}

        
	
        public List<DetailCommandeRegion> findByEtat(String etat) {
		Query query = session.createQuery("select c from DetailCommandeRegion c where c.etat=:etat");
                query.setParameter("etat",etat);
               
                  return query.list();
 }

   public List<DetailCommandeRegion> findDetailCommandeByEtat(CommandeRegion commande, String etat) {
		
		Query query = session.createQuery("select u from DetailCommandeRegion u where u.commandeRegion =:commande and u.etat=:etat");
		query.setParameter("commande",commande);
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }


}
