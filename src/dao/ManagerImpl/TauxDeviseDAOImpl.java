/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.TauxDevise;
import dao.Manager.TauxDeviseDAO;


/**
 *
 * @author Hp
 */
public class TauxDeviseDAOImpl implements TauxDeviseDAO  {
    Session session=HibernateUtil.openSession();




	public TauxDevise findById(int id) {
		return (TauxDevise)session.get(TauxDevise.class, id);
		}

	public void add(TauxDevise TauxDevise) {
		session.beginTransaction();
		session.save(TauxDevise);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public TauxDevise edit(TauxDevise e) {
		
	session.beginTransaction();
	TauxDevise p= (TauxDevise)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(TauxDevise e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<TauxDevise> findAll() {
		return session.createQuery("select c from TauxDevise c").list();
		}


        public TauxDevise findDeviseByTauxDevise(int idDevise) {
		
		Query query = session.createQuery("select c from TauxDevise c where c.devise.id =:idDevise");
		query.setParameter("idDevise",idDevise);

		
                  return (TauxDevise)  query.uniqueResult();
        
 }
	

}
