/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.MatierePremier;
import dao.Entity.PrixDimFour;
import dao.Manager.PrixDimFourDAO;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author Hp
 */
public class PrixDimFourDAOImpl implements PrixDimFourDAO  {
    Session session=HibernateUtil.openSession();




	public PrixDimFour findById(int id) {
		return (PrixDimFour)session.get(PrixDimFour.class, id);
		}

	public void add(PrixDimFour prixDimFour) {
		session.beginTransaction();
		session.save(prixDimFour);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixDimFour edit(PrixDimFour e) {
		
	session.beginTransaction();
	PrixDimFour p= (PrixDimFour)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixDimFour e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixDimFour> findAll() {
            
            return session.createQuery("select c from PrixDimFour c").list();
		
		}
        
            public PrixDimFour findDimensionByPrix(int idDim, int idFour, int idMP) {
		
		Query query = session.createQuery("select u from PrixDimFour u where  matierePremiern.id=:idMP and fournisseur.id=:idFour and dimension.id=:idDim");
		query.setParameter("idDim", idDim);
                query.setParameter("idFour", idFour);
                query.setParameter("idMP", idMP);
		
	return (PrixDimFour)  query.uniqueResult();
        
				}

        
 

   public List<PrixDimFour> findDimensionByFournisseur(String codeFour) {
		
		Query query = session.createQuery("select u from PrixDimFour u where  fournisseur.code=:codeFour");
		query.setParameter("codeFour", codeFour);
               
		
                return query.list();
        
				}
   
   
   
   public List<PrixDimFour> findMatierePremierByRecherche(String rech, String codeFour) {
		
		Query query = session.createQuery("select u from PrixDimFour u where fournisseur.code=:codeFour and matierePremiern.nom like :rech");
		query.setParameter("rech","%"+rech+"%");
               query.setParameter("codeFour", codeFour);
		
                return query.list();
        
				}
   
//   public Iterator listMatierePremiere(String codeFour) {
//		
//		Query query = session.createQuery("select u from PrixDimFour u where  fournisseur.code=:codeFour");
//		query.setParameter("codeFour", codeFour);
//                return query.list().iterator();
//   }
               

  
            
   
  
}