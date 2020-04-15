package dao.ManagerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import Utils.HibernateUtil;
import dao.Manager.StockMPDAO;
import dao.Entity.Magasin;
import dao.Entity.StockMP;

public class StockMPDAOImpl implements StockMPDAO {
	
	Session session=HibernateUtil.openSession();
	

	public void add(StockMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StockMP edit(StockMP e) {
		
	session.beginTransaction();
	StockMP p= (StockMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StockMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<StockMP> findAll() {
		return session.createQuery("select c from StockMP c").list();
		}

	public StockMP findById(int id) {
		return (StockMP)session.get(StockMP.class, id);
		}

	public StockMP findStockByMatierePremier(int id) {
		// TODO Auto-generated method stub
		StockMP stockMP= new StockMP();
		Query query= session.createQuery("select c from StockMP c where matierePremier.id=:id");
		query.setParameter("id", id);
		 
		stockMP= (StockMP) query.uniqueResult();
		
		return stockMP;
	}

	@Override
	public StockMP findStockByMagasinMP(int idMP, int idMA) {
		// TODO Auto-generated method stub
		StockMP stockMP= new StockMP();
		Query query= session.createQuery("select c from StockMP c where matierePremier.id=:idMP and c.magasin.id =:idMA");
		query.setParameter("idMA", idMA);
		query.setParameter("idMP", idMP);
		
		
		stockMP= (StockMP) query.uniqueResult();
		
		return stockMP;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockMP> findAllByMagasin(int idMagasin) {

		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockMP c where magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		
		
		return query.list();
		
	
	}

	@Override
	public List<StockMP> listeStockNouveauMP(int idMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockMP c where magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		return query.list();
	}

	@Override
	public List<StockMP> findSockNonVideByMagasin(int idMagasin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from StockMP c where stock <> 0 and magasin.id=:idMagasin");
				query.setParameter("idMagasin", idMagasin);
				return query.list();
	}
	
	
	@Override
	public List<Magasin> findMagasinByCodeSaufEnParametre(int idMagasin,String codeCatMagasin,String typeMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select distinct c.magasin from StockMP c where c.magasin.id <>:idMagasin and c.magasin.catMagasin =:codeCatMagasin and c.magasin.typeMagasin=:typeMagasin");
		query.setParameter("idMagasin", idMagasin);
		query.setParameter("codeCatMagasin", codeCatMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		
		
		return query.list();
	}
	
	@Override
	public List<StockMP> findStockMin(int  idDepot) {

				Query query= session.createQuery("select c from StockMP c where sum(stock) <= stockMin and magasin.depot.id=:idDepot ");
				query.setParameter("idDepot", idDepot);
				return query.list();
	}
	
	
               public List<StockMP> findStockMPByCateg(int idcatg) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.categorieMp.id =:idcatg");
		query.setParameter("idcatg",idcatg);
               
		
                return query.list();
 }
               
                        public List<StockMP> findStockMPBySubCateg(int idSubcatg) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.categorieMp.subCategorieMp.id =:idSubcatg");
		query.setParameter("idSubcatg",idSubcatg);
               
		
                return query.list();
 }
                            public List<StockMP> findStockMPByMp(String mp) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.code =:mp");
		query.setParameter("mp",mp);
               
		
                return query.list();
 }
                               public List<StockMP> findStockMPByCategAndSubCateg(int idcatg, int idSubcatg){
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.categorieMp.id =:idcatg and c.matierePremier.categorieMp.subCategorieMp.id =:idSubcatg");
		query.setParameter("idcatg",idcatg);
                query.setParameter("idSubcatg",idSubcatg);
		
                return query.list();
 }
                                    public List<StockMP> findStockMPByCategAndMp(int idcatg, String mp) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.categorieMp.id =:idcatg and c.matierePremier.code =:mp");
		query.setParameter("idcatg",idcatg);
                query.setParameter("mp",mp);
		
                return query.list();
 }
     
      public List<StockMP> findStockMPByCategAndMpAndSubCateg(int idcatg, String mp, int idSubcatg) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.categorieMp.id =:idcatg and c.matierePremier.code =:mp and c.matierePremier.categorieMp.subCategorieMp.id =:idSubcatg");
		query.setParameter("idcatg",idcatg);
                query.setParameter("mp",mp);
		query.setParameter("idSubcatg",idSubcatg);
                
                return query.list();
 }                              
         public List<StockMP> findStockMPBySubCategAndMp(String mp, int idSubcatg) {
		
		Query query = session.createQuery("select c from StockMP c where c.matierePremier.code =:mp and c.matierePremier.categorieMp.subCategorieMp.id =:idSubcatg");
                query.setParameter("mp",mp);
		query.setParameter("idSubcatg",idSubcatg);
                
                return query.list();
 }
         

         
}
