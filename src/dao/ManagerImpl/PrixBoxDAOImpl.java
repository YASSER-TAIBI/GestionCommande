/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixBox;
import dao.Manager.PrixBoxDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixBoxDAOImpl implements PrixBoxDAO   {
    Session session=HibernateUtil.openSession();




	public PrixBox findById(int id) {
		return (PrixBox)session.get(PrixBox.class, id);
		}

	public void add(PrixBox prixBox) {
		session.beginTransaction();
		session.save(prixBox);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixBox edit(PrixBox e) {
		
	session.beginTransaction();
	PrixBox p= (PrixBox)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixBox e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixBox> findAll() {
		return session.createQuery("select c from PrixBox c").list();
		}

            public PrixBox findDimensionByPrixBox(int idDim, int idFour, int idCg, int idGram, int idTcb,int idInt) {
		
		Query query = session.createQuery("select u from PrixBox u where u.categorieMp.id=:idCg and u.fournisseur.id=:idFour and u.dimension.id=:idDim and u.grammage.id=:idGram and u.typeCartonBox.id=:idTcb and u.intervalle.id=:idInt");
		query.setParameter("idDim", idDim);
                query.setParameter("idFour", idFour);
                query.setParameter("idCg", idCg);
                query.setParameter("idGram", idGram);
                query.setParameter("idTcb", idTcb);
                query.setParameter("idInt", idInt);

		
	return (PrixBox)  query.uniqueResult();
        
				}
            
            
               public List<PrixBox> findPrixBoxByFour(int idFour) {
		
		Query query = session.createQuery("select c from PrixBox c where c.fournisseur.id=:idFour");
		query.setParameter("idFour",idFour);
               
		
                return query.list();
 }
               

                    public PrixBox findPrixBoxByRegion(int idDim, int idCg, int idGram, int idTcb,int idInt) {
		
		Query query = session.createQuery("select u from PrixBox u where u.categorieMp.id=:idCg and u.dimension.id=:idDim and u.grammage.id=:idGram and u.typeCartonBox.id=:idTcb and u.intervalle.id=:idInt");
		query.setParameter("idDim", idDim);
                query.setParameter("idCg", idCg);
                query.setParameter("idGram", idGram);
                query.setParameter("idTcb", idTcb);
                query.setParameter("idInt", idInt);

		
	return (PrixBox)  query.uniqueResult();
        
				}
                    
                        public List<PrixBox>  findPrixBoxByCategorieMp(int CatMp , int Four) {
		
		Query query = session.createQuery("select u from PrixBox u where  u.categorieMp.id=:CatMp and u.fournisseur.id =:Four");
		query.setParameter("CatMp", CatMp);
                query.setParameter("Four", Four);
		
	return  query.list();
	      
    }
                        
                       public List<PrixBox>  findPrixBoxByCategorieMpTMP(int CatMp) {
		
		Query query = session.createQuery("select u from PrixBox u where  u.categorieMp.id=:CatMp");
		query.setParameter("CatMp", CatMp);
		
	return  query.list();
	      
    }
                        
}
