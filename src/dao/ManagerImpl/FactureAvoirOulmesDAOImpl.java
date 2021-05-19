package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.FactureAvoirOulmes;
import dao.Manager.FactureAvoirOulmesDAO;


public class FactureAvoirOulmesDAOImpl implements FactureAvoirOulmesDAO {
	Session session=HibernateUtil.openSession();

	public void add(FactureAvoirOulmes e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAvoirOulmes edit(FactureAvoirOulmes e) {
		
	session.beginTransaction();
	FactureAvoirOulmes p= (FactureAvoirOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(FactureAvoirOulmes e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<FactureAvoirOulmes> findAll() {
		return session.createQuery("select c from FactureAvoirOulmes c").list();
		}
        
	public FactureAvoirOulmes findById(int id) {
		return (FactureAvoirOulmes)session.get(FactureAvoirOulmes.class, id);
		}
        
   	public List<FactureAvoirOulmes>  findAvoirOulmesByEtat(String etat) {
		
		Query query = session.createQuery("select u from FactureAvoirOulmes u where  u.etat =:etat");
		query.setParameter("etat", etat);
		
	return  query.list();

				}

    public int getMaxId() {
    
                int id;
                
        Query query= session.createQuery("select Max(id) from FactureAvoirOulmes c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id;
    }
    
           public List<Object[]> findBySituationGlobal() {
        Query query=  session.createQuery("select c.prixOulmes, sum(c.qte), c.client from FactureAvoirOulmes c group by c.prixOulmes");

        return query.list();
    }
           
                 public List<Object[]> findBySituationGlobalClient() {
        Query query=  session.createQuery("select c.client, sum(c.qte), c.prixOulmes from FactureAvoirOulmes c group by c.client");

        return query.list();
    }
           
                    public List<Object[]> findBySituationGlobalArticle(String code) {
        Query query=  session.createQuery("select c.prixOulmes, c.qte, c.client from FactureAvoirOulmes c where c.prixOulmes.produit.code =:code");
        query.setParameter("code", code);
        return query.list();
    }
                           
         public List<Object[]> findFactureOulmesByCodeAndClient(String code, String client) {
        Query query=  session.createQuery("select u.prixOulmes, u.qte ,u.dateSaisie, u.designation from FactureAvoirOulmes u where u.prixOulmes.produit.code =:code and u.client =:client");
        query.setParameter("code", code);
        query.setParameter("client", client);
        
        return query.list();
    }
         
             public List<FactureAvoirOulmes> findByFactureAvoirOulmes() {
        Query query=  session.createQuery("select u from FactureAvoirOulmes u where u.numLivraison not in ('0021409','0021415','1669587') group by u.numLivraison");
        
        return query.list();
    }
         
}
