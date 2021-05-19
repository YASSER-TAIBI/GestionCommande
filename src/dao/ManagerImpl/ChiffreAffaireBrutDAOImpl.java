/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.ChiffreAffaireBrut;
import dao.Manager.ChiffreAffaireBrutDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class ChiffreAffaireBrutDAOImpl implements ChiffreAffaireBrutDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public ChiffreAffaireBrut findById(int id) {

return (ChiffreAffaireBrut)session.get(ChiffreAffaireBrut.class, id);    }

    @Override
    public void add(ChiffreAffaireBrut chiffreAffaireBrut) {

               session.beginTransaction();
		session.save(chiffreAffaireBrut);
		
		session.getTransaction().commit();    }

    @Override
    public ChiffreAffaireBrut edit(ChiffreAffaireBrut e) {

session.beginTransaction();
	ChiffreAffaireBrut p= (ChiffreAffaireBrut)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(ChiffreAffaireBrut e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<ChiffreAffaireBrut> findAll() {

return session.createQuery("select c from ChiffreAffaireBrut c").list();

    }
    
    
    public ChiffreAffaireBrut findByNumMois(int mois) {
		
		Query query = session.createQuery("select u from ChiffreAffaireBrut u where u.numMois=:mois");
		query.setParameter("mois", mois);
                 return (ChiffreAffaireBrut)query.uniqueResult();
 }
}
