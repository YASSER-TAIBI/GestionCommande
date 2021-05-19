package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.InitialEmballage;
import dao.Manager.InitialEmballageDAO;


public class InitialEmballageDAOImpl implements InitialEmballageDAO {
	Session session=HibernateUtil.openSession();

	public void add(InitialEmballage e) {
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		//return p;
	}

	public InitialEmballage edit(InitialEmballage e) {
		
	session.beginTransaction();
	InitialEmballage p= (InitialEmballage)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(InitialEmballage e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<InitialEmballage> findAll() {
		return session.createQuery("select c from InitialEmballage c ").list();
		}
                
        
        public List<Object[]> findBySituationEmballage() {
        Query query=  session.createQuery("select c.prixOulmes, sum(c.qteEmballage) from InitialEmballage c group by c.prixOulmes");

        return query.list();
    }
        
        public List<Object[]> findBySituationEmballageAndClientAndFour(String client, String four) {
        Query query=  session.createQuery("select c.prixOulmes, sum(c.qteEmballage) from InitialEmballage c where c.clientMP.nom =:client and c.fourisseur.nom =:four group by c.prixOulmes");
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }
         public List<Object[]> findBySituationEmballageAndMois(int prixOulmes) {
        Query query=  session.createQuery("select MONTH(c.dateInitial),c.prixOulmes ,sum(c.qteEmballage) from InitialEmballage c where c.prixOulmes.id =:prixOulmes group by MONTH(c.dateInitial)");
        query.setParameter("prixOulmes", prixOulmes);
        return query.list();
    }
        
        public List<Object[]> findBySituationEmballageAndMoisAndClientAndFour(int prixOulmes,String client, String four) {
        Query query=  session.createQuery("select MONTH(c.dateInitial),c.prixOulmes ,sum(c.qteEmballage) from InitialEmballage c where c.prixOulmes.id =:prixOulmes and c.clientMP.nom =:client and c.fourisseur.nom =:four group by MONTH(c.dateInitial)");
        query.setParameter("prixOulmes", prixOulmes);
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }
}
