/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.ClientMP;
import dao.Manager.ClientMPDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author h
 */
public class ClientMPDAOImpl implements ClientMPDAO {
    Session session=HibernateUtil.openSession();

	public ClientMP findUtilisateurByLoginMotPasse(String login,
			String motPasse) {
		
		Query query = session.createQuery("select u from Utilisateur u where  u.login=:login and u.password=:motPasse");
		query.setParameter("login", login);
		query.setParameter("motPasse", motPasse);
	return (ClientMP) query.uniqueResult();
	      

				}

	public ClientMP findById(int id) {
		return (ClientMP)session.get(ClientMP.class, id);
		}

	
	public void add(ClientMP clientMP) {
		session.beginTransaction();
		session.save(clientMP);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public ClientMP edit(ClientMP e) {
		
	session.beginTransaction();
	ClientMP p= (ClientMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	
	public void delete(ClientMP e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	
	public List<ClientMP> findAll() {
		return session.createQuery("select c from ClientMP c").list();
		}

    @Override
    public int getMaxId() {
           int id;
        Query query= session.createQuery("select Max(id) from ClientMP c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id;
    }
	
	
 public List<ClientMP> findClientMPByRechercheNom(String nom) {
		
		Query query = session.createQuery("select u from ClientMP u where nom like :nom");
		query.setParameter("nom","%"+nom+"%");
               
		
                return query.list();
 }
 
  public ClientMP findClientMPByNom(String nom) {
		
		Query query = session.createQuery("select u from ClientMP u where u.nom =:nom");
		query.setParameter("nom",nom);

               return (ClientMP) query.uniqueResult();
 }
 
 
 public List<ClientMP> findClientMPByRechercheCode(String code) {
		
		Query query = session.createQuery("select u from ClientMP u where Code like :code");
		query.setParameter("code","%"+code+"%");
               
		
                return query.list();
 }


 
}
