/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixOulmes;
import dao.Manager.PrixOulmesDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixOulmesDAOImpl implements PrixOulmesDAO   {
    Session session=HibernateUtil.openSession();




	public PrixOulmes findById(int id) {
		return (PrixOulmes)session.get(PrixOulmes.class, id);
		}

	public void add(PrixOulmes prixOulmes) {
		session.beginTransaction();
		session.save(prixOulmes);
		session.getTransaction().commit();
		//return p;
	}

	
	public PrixOulmes edit(PrixOulmes e) {
		
	session.beginTransaction();
	PrixOulmes p= (PrixOulmes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(PrixOulmes e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<PrixOulmes> findAll() {
		return session.createQuery("select c from PrixOulmes c where c.etat = 'Lancé'").list();
		}
        
                            public List<PrixOulmes> findPrixOulmes() {
		Query query = session.createQuery("select u from PrixOulmes u where u.client ='Sans' and u.lieuLivraison ='Sans' and u.etat = 'Lancé'");
                return query.list();
 }
        
                    public List<PrixOulmes> findPrixFilmByClient(String client) {
		
		Query query = session.createQuery("select c from PrixOulmes c where c.client =:client and c.etat = 'Lancé'");
		query.setParameter("client",client);
               
		
                return query.list();
 }
        
        public PrixOulmes findPrixOulmesByFourAndArt( String Four, int idArt , String client , String lieu) {
		
		Query query = session.createQuery("select u from PrixOulmes u where u.produit.id =:idArt and u.fournisseur =:Four and u.client =:client and u.lieuLivraison =:lieu and u.etat = 'Lancé'");
                query.setParameter("Four", Four);
                query.setParameter("idArt", idArt);
                query.setParameter("client", client);
                query.setParameter("lieu", lieu);
                return (PrixOulmes)  query.uniqueResult();
        
				}
        
                public PrixOulmes findPrixOulmesByArt( int idArt , String client , String lieu) {
		
		Query query = session.createQuery("select u from PrixOulmes u where u.produit.id =:idArt and  u.client =:client and u.lieuLivraison =:lieu and u.etat = 'Lancé'");
                query.setParameter("idArt", idArt);
                query.setParameter("client", client);
                query.setParameter("lieu", lieu);
                return (PrixOulmes)  query.uniqueResult();
        
				}
        
          public PrixOulmes findPrixOulmesByCodeArt( String codeArt ) {
		
		Query query = session.createQuery("select u from PrixOulmes u where u.produit.code =:codeArt and u.etat = 'Lancé'");
                query.setParameter("codeArt", codeArt);
                
                
                return (PrixOulmes)  query.uniqueResult();
        
				}
          
           public PrixOulmes findPrixOulmesByCodeArt( String codeArt , String client , String lieu) {
		
		Query query = session.createQuery("select u from PrixOulmes u where u.produit.code =:codeArt and u.client=:client and u.lieuLivraison=:lieu and u.etat = 'Lancé'");
                query.setParameter("codeArt", codeArt);
                query.setParameter("client", client);
                query.setParameter("lieu", lieu);
                
                return (PrixOulmes)  query.uniqueResult();
        
				}
          
                  public List<PrixOulmes> findPrixFilmByFour(String Four) {
		
		Query query = session.createQuery("select c from PrixOulmes c where c.fournisseur =:Four and c.etat = 'Lancé'");
		query.setParameter("Four",Four);
               
		
                return query.list();
 }
                      
                   public List<String> findPrixOulmesByClient(String client) {
      Query query = session.createQuery("select DISTINCT u.lieuLivraison from PrixOulmes u where u.client =:client and u.etat = 'Lancé'");
                query.setParameter("client", client);
        return query.list();
    }

                    public List<PrixOulmes> findPrixOulmesByLieu(String lieu) {
		
		Query query = session.createQuery("select c from PrixOulmes c where c.lieuLivraison =:lieu and c.etat = 'Lancé'");
		query.setParameter("lieu",lieu);
               
		
                return query.list();
 }
                                   
                    public PrixOulmes findByCode(String four) {
		// TODO Auto-generated method stub
		PrixOulmes prixOulmes= new PrixOulmes();
		Query query= session.createQuery("select c from PrixOulmes c where c.fournisseur =:four and c.etat = 'Lancé'");
		query.setParameter("four", four);
		
		prixOulmes= (PrixOulmes) query.uniqueResult();
		
		return prixOulmes;
	}
                                  
                    public List<PrixOulmes> findByPallete() {
		// TODO Auto-generated method stub

		Query query= session.createQuery("select c from PrixOulmes c where c.produit.palette = 1 and c.etat = 'Lancé'");
		
		return query.list();
	}
}
