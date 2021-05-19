package dao.ManagerImpl;

import Utils.Constantes;
import java.util.List;

import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.SubCategorieMPDAO;
import dao.Entity.SubCategorieMp;
import org.hibernate.Query;

public class SubCategorieMPAOImpl implements SubCategorieMPDAO {
	
	Session session=HibernateUtil.openSession();

	public void add(SubCategorieMp e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SubCategorieMp edit(SubCategorieMp e) {
		
	session.beginTransaction();
	SubCategorieMp p= (SubCategorieMp)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(SubCategorieMp e) {
		
		session.beginTransaction();

		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<SubCategorieMp> findAll() {
		return session.createQuery("select c from SubCategorieMp c where c.etat = 'Lancé'").list();
		}

	public SubCategorieMp findById(Long id) {
		return (SubCategorieMp)session.get(SubCategorieMp.class, id);
		}

        	public List<SubCategorieMp> SubCategorieMpByBoxCartonAdf() {

		Query query= session.createQuery("select c from SubCategorieMp c where c.etat = 'Lancé' and (c.nom=:box or c.nom=:carton or c.nom=:adf or c.nom=:filmGold or c.nom=:filmNormal)");
		query.setParameter("box", Constantes.BOX );
                query.setParameter("carton",Constantes.CARTON );
                query.setParameter("adf",Constantes.ADHESIF );
                query.setParameter("filmGold",Constantes.FILM_GOLD );
                query.setParameter("filmNormal",Constantes.FILM_NORMAL );
		
		
		return query.list();
//		
                }
                
                public List<SubCategorieMp> findByCodeSubCategorieMp(String code) {
		
		Query query = session.createQuery("select u from SubCategorieMp u where u.code code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<SubCategorieMp> findBylibelleSubCategorieMp(String lib) {
		
		Query query = session.createQuery("select u from SubCategorieMp u where u.nom like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }

                
}
