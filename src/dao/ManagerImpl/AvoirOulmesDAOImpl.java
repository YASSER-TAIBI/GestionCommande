package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.AvoirOulmes;
import dao.Manager.AvoirOulmesDAO;
import java.math.BigDecimal;


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
         
        
        public List<Object[]> findBySituationGlobalArticle(String code) {
        Query query=  session.createQuery("select u.prixOulmes, u.qte, u.client from AvoirOulmes u where u.prixOulmes.produit.code =:code");
        query.setParameter("code", code);

        return query.list();
    }
                   
                                
        
         public List<AvoirOulmes> findAvoirOulmesByBonAvoir(String bonAv, String etat) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.numLivraison =:bonAv and u.etat =:etat");
		query.setParameter("bonAv", bonAv);
		query.setParameter("etat", etat);
                
	return  query.list();
	      

				}
         
         public List<AvoirOulmes>  findAvoirOulmesByClient(String client) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.client =:client  and u.etat = 'BL Avoir'");
		query.setParameter("client", client);
		
	return  query.list();

				}
         
         public List<AvoirOulmes>  findAvoirOulmesByBonAvoirAndClient(String bonAv ,String client) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.numLivraison =:bonAv and u.client =:client and u.etat = 'BL Avoir'");
		query.setParameter("bonAv", bonAv);
		query.setParameter("client", client);
	return  query.list();
	      

				}

            
               public List<AvoirOulmes>  findAvoirOulmesByBonAvoir() {
		
		Query query = session.createQuery("select u from AvoirOulmes u group by u.numLivraison");
                
	return  query.list();
				}
               
                    public AvoirOulmes findBonRetourByNumCommAndNumLiv(String bonAvoir) {
		Query query = session.createQuery("select u from AvoirOulmes u where u.numLivraison=:bonAvoir ");
		query.setParameter("bonAvoir",bonAvoir);
		
                return (AvoirOulmes)query.uniqueResult();
 }
                    
                    
                         public List<AvoirOulmes> findByAllFilter(String etat, String req) {
		
		Query query = session.createQuery("select u from AvoirOulmes u where u.etat=:etat"+req);
		query.setParameter("etat", etat);
                
	return  query.list();
				}
                         
                  public List<Object[]> findBySituationGlobalClient() {
        Query query=  session.createQuery("select c.client, SUM(c.avoir) , SUM(c.factureAvoir), SUM(c.ecart) from AvoirOulmes c group by c.client");

        return query.list();
    }
           
            
}
