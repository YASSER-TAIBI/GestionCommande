/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.ReferencePromo;
import dao.Manager.ReferencePromoDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;


/**
 *
 * @author h
 */
public class ReferencePromoDAOImpl implements ReferencePromoDAO{
    Session session=HibernateUtil.openSession();

    
	public ReferencePromo findById(int id) {
		return (ReferencePromo)session.get(ReferencePromo.class, id);
		}

   
	public void add(ReferencePromo referencePromo) {
		session.beginTransaction();
		session.save(referencePromo);
		
		session.getTransaction().commit();
		//return p;
	}


	public ReferencePromo edit(ReferencePromo e) {
		
	session.beginTransaction();
	ReferencePromo p= (ReferencePromo)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(ReferencePromo e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<ReferencePromo> findAll() {
              Query query = session.createQuery("select c from ReferencePromo c ");
		
	return  query.list();
    }

    
         public ReferencePromo findByPrixOulmes(int prixOulmes) {
		
		Query query = session.createQuery("select u from ReferencePromo u where u.prixOulmes.id=:prixOulmes and u.prixOulmes.fournisseur = 'OULMES ETAT' and u.etat = 'Lancé'");
                query.setParameter("prixOulmes", prixOulmes);
                return (ReferencePromo)  query.uniqueResult();
        
				}
    
}
