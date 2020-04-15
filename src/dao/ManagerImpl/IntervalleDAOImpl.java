/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Intervalle;
import dao.Manager.IntervalleDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class IntervalleDAOImpl implements IntervalleDAO   {
    Session session=HibernateUtil.openSession();




	public Intervalle findById(int id) {
		return (Intervalle)session.get(Intervalle.class, id);
		}

	public void add(Intervalle intervalle) {
		session.beginTransaction();
		session.save(intervalle);
		session.getTransaction().commit();
		//return p;
	}

	
	public Intervalle edit(Intervalle e) {
		
	session.beginTransaction();
	Intervalle p= (Intervalle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Intervalle e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Intervalle> findAll() {
		return session.createQuery("select c from Intervalle c").list();
		}
        
        
           public List<Intervalle> findIntervalleByQte(int qte) {
		
		Query query = session.createQuery("select c from Intervalle c where :qte between c.valeurMin and c.valeurMax" );
		query.setParameter("qte",qte);
               
           
		
                return query.list();

           }
    
           
	public  List<Intervalle> findIntervalleByCode(String code) {
		
		Query query = session.createQuery("select u from Intervalle u where  u.code=:code");
		query.setParameter("code", code);
	  return query.list();
	      
				}
        
        public Intervalle findIntervalleByCodeIO(String code) {
		
		Query query = session.createQuery("select u from Intervalle u where  u.code=:code");
		query.setParameter("code", code);
	  return (Intervalle)  query.uniqueResult();
	      
				}
        
        
        public List<Intervalle> findIntervalleSufCodeI0ByCode(String codeI0) {
		
		Query query = session.createQuery("select u from Intervalle u where  u.code<>:codeI0");
		query.setParameter("codeI0", codeI0);
                
	  return query.list();
	      
				}
           
}
