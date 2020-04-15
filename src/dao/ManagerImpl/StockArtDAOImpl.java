package dao.ManagerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import Utils.HibernateUtil;
import dao.Entity.Magasin;
import dao.Entity.StockArt;
import dao.Manager.StockArtDAO;

public class StockArtDAOImpl implements StockArtDAO {
	
	Session session=HibernateUtil.openSession();
	

	public void add(StockArt e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StockArt edit(StockArt e) {
		
	session.beginTransaction();
	StockArt p= (StockArt)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StockArt p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<StockArt> findAll() {
		return session.createQuery("select c from StockArt c").list();
		}

	public StockArt findById(int id) {
		return (StockArt)session.get(StockArt.class, id);
		}


	@Override
	public StockArt findStockByMagasinArt(int idPOlm, int idMA) {
		// TODO Auto-generated method stub
		StockArt stockArt= new StockArt();
		Query query= session.createQuery("select c from StockArt c where c.prixOulmes.id =:idPOlm and c.magasin.id =:idMA");
		query.setParameter("idMA", idMA);
		query.setParameter("idPOlm", idPOlm);
		
		
		stockArt= (StockArt) query.uniqueResult();
		
		return stockArt;
	}
    
}
