/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailReception;
import dao.Manager.DetailReceptionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author h
 */
public class DetailReceptionDAOImpl implements DetailReceptionDAO {
      Session session=HibernateUtil.openSession();



	public DetailReception findById(int id) {
		return (DetailReception)session.get(DetailReception.class, id);
		}

	public void add(DetailReception detailReception) {
		session.beginTransaction();
		session.save(detailReception);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailReception edit(DetailReception e) {
		
	session.beginTransaction();
	DetailReception p= (DetailReception)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailReception e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailReception> findAll() {
		return session.createQuery("select c from DetailReception c").list();
		}

    @Override
    public int getMaxId() {
    
        
                int id;
                
        Query query= session.createQuery("select Max(id) from DetailReception c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult()+1;
        
        
       return id;
    }
    
	public List<DetailReception>  findReceptionByEtat(String etat) {
		
		Query query = session.createQuery("select u from DetailReception u where  u.etat=:etat");
		query.setParameter("etat", etat);
		
	return  query.list();
	      

        
        
				}

	
public List<DetailReception>  findReceptionBycode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailReception c where c.numReception=:code and c.quantiteRecu<>0");
		query.setParameter("code", code);
		
		return query.list();

}

public DetailReception findByNumLiv(String nLiv) {
		
		Query query = session.createQuery("select u from DetailReception u where u.livraisonFour=:nLiv");
		query.setParameter("nLiv", nLiv);

     return (DetailReception) query.uniqueResult();
        }

           public List<DetailReception> findByCommande(String ncom ) {
		
		Query query = session.createQuery("select c from DetailReception c where c.detailCommande.commande.nCommande =:ncom and c.numReception Like 'RCP %' group by c.livraisonFour" );
		query.setParameter("ncom",ncom);
        
                return query.list();
                
 }
    
                 public List<DetailReception> findByCommandePF(String ncom ) {
		
		Query query = session.createQuery("select c from DetailReception c where c.detailCommande.commande.nCommande =:ncom and c.numReception Like 'RCP_PF %' group by c.livraisonFour" );
		query.setParameter("ncom",ncom);
        
                return query.list();
                
 }

}
