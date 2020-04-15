package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.AvoirOulmes;
import dao.Manager.AvoirOulmesDAO;


public class AvoirOulmesDAOImpl implements AvoirOulmesDAO {
	Session session=HibernateUtil.openSession();

	public void add(AvoirOulmes e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public AvoirOulmes edit(AvoirOulmes e) {
		
	session.beginTransaction();
	AvoirOulmes p= (AvoirOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(AvoirOulmes e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<AvoirOulmes> findAll() {
		return session.createQuery("select c from AvoirOulmes c").list();
		}

	public AvoirOulmes findById(int id) {
		return (AvoirOulmes)session.get(AvoirOulmes.class, id);
		}
        
        
   	public List<AvoirOulmes>  findAvoirOulmesByEtat(String etat) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where  u.etat =:etat");
		query.setParameter("etat", etat);
		
	return  query.list();
	      

				}

        public List<Object[]> findBySituationGlobal() {
        Query query=  session.createQuery("select c.prixOulmes, sum(c.qte), c.client from AvoirOulmes c group by c.prixOulmes");

        return query.list();
    }
          
         public List<Object[]> findBySituationGlobalClient() {
        Query query=  session.createQuery("select c.client, sum(c.qte),c.prixOulmes from AvoirOulmes c group by c.client");

        return query.list();
    }
        
        public List<Object[]> findBySituationGlobalArticle(String code) {
        Query query=  session.createQuery("select u.prixOulmes, u.qte, u.client from AvoirOulmes u where u.prixOulmes.produit.code =:code");
        query.setParameter("code", code);

        return query.list();
    }
                   
                                  
         public List<Object[]> findAvoirOulmesByCodeAndClient(String code, String client) {
        Query query=  session.createQuery("select u.prixOulmes, u.qte ,u.dateSaisie, u.designation from AvoirOulmes u where u.prixOulmes.produit.code =:code and u.client =:client");
        query.setParameter("code", code);
        query.setParameter("client", client);
        
        return query.list();
    }
        
         public List<AvoirOulmes>  findAvoirOulmesByBonAvoir(String bonAv) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.numLivraison =:bonAv");
		query.setParameter("bonAv", bonAv);
		
	return  query.list();
	      

				}
         
         public List<AvoirOulmes>  findAvoirOulmesByClient(String client) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.client =:client");
		query.setParameter("client", client);
		
	return  query.list();

				}
         
         public List<AvoirOulmes>  findAvoirOulmesByBonAvoirAndClient(String bonAv ,String client) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.numLivraison =:bonAv and u.client =:client");
		query.setParameter("bonAv", bonAv);
		query.setParameter("client", client);
	return  query.list();
	      

				}
        
}
