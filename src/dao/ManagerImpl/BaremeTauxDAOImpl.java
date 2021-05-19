/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.BaremeTaux;
import dao.Manager.BaremeTauxDAO;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class BaremeTauxDAOImpl implements BaremeTauxDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public BaremeTaux findById(int id) {

return (BaremeTaux)session.get(BaremeTaux.class, id);    }

    @Override
    public void add(BaremeTaux baremeTaux) {

               session.beginTransaction();
		session.save(baremeTaux);
		
		session.getTransaction().commit();    }

    @Override
    public BaremeTaux edit(BaremeTaux e) {

session.beginTransaction();
	BaremeTaux p= (BaremeTaux)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(BaremeTaux e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<BaremeTaux> findAll() {

return session.createQuery("select c from BaremeTaux c").list();

    }
    
        public BaremeTaux findByClasseTRO(BigDecimal tro) {
		Query query = session.createQuery("select u from BaremeTaux u where u.tauxRealisationObjectifMin < :tro and u.tauxRealisationObjectifMax >= :tro");
                query.setParameter("tro",tro);

                   return (BaremeTaux)query.uniqueResult();
 }
    
}
