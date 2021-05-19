package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.ClientDAO;
import dao.Entity.Client;

public class ClientDAOImpl implements ClientDAO {
    
	Session session=HibernateUtil.openSession();

	public void add(Client e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

        
	public Client edit(Client e) {
		
	session.beginTransaction();
	Client p= (Client)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Client p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


        @Override
	public List<Client> findAll() {
		return session.createQuery("select c from Client c").list();
		}

        @Override
	public Client findById(int id) {
		return (Client)session.get(Client.class, id);
		}

	

}
