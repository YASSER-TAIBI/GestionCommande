package dao.ManagerImpl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.StockPFDAO;
import dao.Entity.StockPF;

public class StockPFDAOImpl implements StockPFDAO {
	Session session=HibernateUtil.openSession();


	public void add(StockPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StockPF edit(StockPF e) {
		
	session.beginTransaction();
	StockPF p= (StockPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StockPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	
	public List<StockPF> findAll() {
		return session.createQuery("select c from StockPF c").list();
		}

	public StockPF findById(int id) {
		return (StockPF)session.get(StockPF.class, id);
		}

        
        public List<Object[]> findStockPFByMp(int mp, int magasin, String categMag) {
		
		Query query = session.createQuery("select sum(c.stock), c.magasin , c.matierePremier  from StockPF c where c.matierePremier.id =:mp and c.magasin.id <> :magasin c.magasin.catMagasin =:categMag group by  c.magasin.id");
                query.setParameter("mp",mp);
		query.setParameter("magasin",magasin);
                query.setParameter("categMag",categMag);
                
                return query.list();
 }    
        
        public StockPF findStockByMagasinMP(int idPF, int idMA) {
		// TODO Auto-generated method stub
		StockPF stockPF= new StockPF();
		Query query= session.createQuery("select c from StockPF c where matierePremier.id=:idPF and magasin.id=:idMA");
		query.setParameter("idMA", idMA);
		query.setParameter("idPF", idPF);
		
		
		stockPF= (StockPF) query.uniqueResult();
		
		return stockPF;
	}

        
	

	
	


}
