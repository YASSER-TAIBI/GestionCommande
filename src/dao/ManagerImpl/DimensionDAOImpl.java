/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Dimension;
import dao.Manager.DimensionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author Hp
 */
public class DimensionDAOImpl implements DimensionDAO  {
    Session session=HibernateUtil.openSession();




	public Dimension findById(int id) {
		return (Dimension)session.get(Dimension.class, id);
		}

	public void add(Dimension utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public Dimension edit(Dimension e) {
		
	session.beginTransaction();
	Dimension p= (Dimension)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Dimension e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<Dimension> findAll() {
            
            return session.createQuery("select c from Dimension c where c.etat = 'Lancé'").list();
		
		}

    @Override
    public int getMaxId() {
        
                int id;
        Query query= session.createQuery("select Max(id) from Dimension c  where c.etat = 'Lancé'");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id;
	
    }
    
    public List<Dimension>  findDimensionByFamille(String famille) {
		
		Query query = session.createQuery("select u from Dimension u where u.famille=:famille and u.etat = 'Lancé'");
		query.setParameter("famille", famille);
		
	return  query.list();
	      
    }

    
   public List<Dimension>  findDimensionByID(int id) {
		
		Query query = session.createQuery("select u from Dimension u where u.categorieMp.id=:id and u.etat = 'Lancé'");
		query.setParameter("id", id);
		
	return  query.list();
	      
    }
   
   	
	public Dimension findByCode(String code) {
		// TODO Auto-generated method stub
		Dimension dimension= new Dimension();
		Query query= session.createQuery("select c from Dimension c where c.libelle =:code and c.etat = 'Lancé'");
		query.setParameter("code", code);
		
		dimension= (Dimension) query.uniqueResult();
		
		return dimension;
	}
        
              public Dimension findDimensionByCode(String code) {
		
		Query query = session.createQuery("select u from Dimension u where  u.code=:code and u.etat = 'Lancé' ");
		query.setParameter("code", code);
	  return (Dimension)  query.uniqueResult();
	      
				}
              
                 public List<Dimension> findDimensionByCodeDIM(String code) {
		
		Query query = session.createQuery("select u from Dimension u where  u.code=:code and u.etat = 'Lancé'");
		query.setParameter("code", code);
                
		return  query.list();
	      
				}
              
               public List<Dimension>  findDimensionByCat(String cat) {
		
		Query query = session.createQuery("select u from Dimension u where u.famille=:cat and u.etat = 'Lancé'");
		query.setParameter("cat", cat);
		
	return  query.list();
	      
    }
         
               	 public List<Dimension> findByCodeDimension(String code) {
		
		Query query = session.createQuery("select u from Dimension u where u.code like:code and u.etat = 'Lancé'");
		query.setParameter("code","%"+code+"%");
		
                return query.list();
 }
    
         	 public List<Dimension> findBylibelleDimension(String lib) {
		
		Query query = session.createQuery("select u from Dimension u where u.libelle like:lib and u.etat = 'Lancé'");
		query.setParameter("lib","%"+lib+"%");
		
                return query.list();
 }

               
               
}