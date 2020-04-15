/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Reglement;
import dao.Manager.CommandeDAO;
import dao.Manager.ReglementDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class ReglementDAOImpl implements ReglementDAO{
    Session session=HibernateUtil.openSession();

  	public Reglement findById(int id) {
		return (Reglement)session.get(Reglement.class, id);
		}

   
	public void add(Reglement reglement) {
		session.beginTransaction();
		session.save(reglement);
		
		session.getTransaction().commit();
		//return p;
	}


	public Reglement edit(Reglement e) {
		
	session.beginTransaction();
	Reglement p= (Reglement)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Reglement e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<Reglement> findAll() {
              return session.createQuery("select c from Reglement c").list();
    }

    
 
    public int getMaxId() {
          int id;
        Query query= session.createQuery("select Max(id) from Reglement c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id;
    }

     public List<Reglement> findFourByRechercheNomReglement(int four,int clt) {
		
		Query query = session.createQuery("select c from Reglement c where c.clientMP.id =:clt and c.fournisseur.id =:four");
		query.setParameter("four",four);
                query.setParameter("clt",clt);
   
                return query.list();
}
     
     
      public List<Reglement> findFourByRechercheNom(String four) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.fourisseur =:four" );
		query.setParameter("four",four);
               
           
		
                return query.list();
 }
      
                public List<Reglement> findReglementByFacture(String code,int client ,int four) {
		
		Query query = session.createQuery("select u from Reglement u where u.clientMP.id=:client and u.fournisseur.id =:four and u.numFacture like :code");
		query.setParameter("code","%"+code+"%");
                query.setParameter("client",client);
                query.setParameter("four",four);
               
		
                return query.list();
 }
                
                 public List<Reglement> findReglementByCheque(String code,int client ,int four) {
		
		Query query = session.createQuery("select u from Reglement u where u.clientMP.id =:client and u.fournisseur.id =:four and u.numCritique like :code");
		query.setParameter("code","%"+code+"%");
                query.setParameter("client",client);
                query.setParameter("four",four);
               
		
                return query.list();
 }
}