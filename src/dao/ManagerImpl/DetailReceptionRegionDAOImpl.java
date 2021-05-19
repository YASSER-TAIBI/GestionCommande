/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailReceptionRegion;
import dao.Manager.DetailReceptionRegionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author h
 */
public class DetailReceptionRegionDAOImpl implements DetailReceptionRegionDAO {
      Session session=HibernateUtil.openSession();



	public DetailReceptionRegion findById(int id) {
		return (DetailReceptionRegion)session.get(DetailReceptionRegion.class, id);
		}

	public void add(DetailReceptionRegion detailReceptionRegion) {
		session.beginTransaction();
		session.save(detailReceptionRegion);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailReceptionRegion edit(DetailReceptionRegion e) {
		
	session.beginTransaction();
	DetailReceptionRegion p= (DetailReceptionRegion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailReceptionRegion e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailReceptionRegion> findAll() {
		return session.createQuery("select c from DetailReceptionRegion c").list();
		}


        public List<DetailReceptionRegion>  findReceptionRegionBydetailCom(int detailCom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailReceptionRegion c where c.detailCommandeRegion.id=:detailCom");
		query.setParameter("detailCom", detailCom);
		
		return query.list();

}
        
}
