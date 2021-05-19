package dao.ManagerImpl;

import dao.Entity.BonLivraison;
import dao.Entity.Commande;
import dao.Manager.BonLivraisonDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Fournisseur;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class BonLivraisonDAOImpl implements BonLivraisonDAO {
     Session session=HibernateUtil.openSession();
  
     
     	public List<BonLivraison>  findBonLivraisonByEtat(String etat) {
		
		Query query = session.createQuery("select u from BonLivraison u where  u.etat=:etat");
		query.setParameter("etat", etat);
		
	return  query.list();
	      

				}
     
     public BonLivraison findById(int id) {
		return (BonLivraison)session.get(BonLivraison.class, id);
		}

 
    
	public void add(BonLivraison bonLivraison) {
		session.beginTransaction();
		session.save(bonLivraison);
		
		session.getTransaction().commit();
		//return p;
	}


	public BonLivraison edit(BonLivraison e) {
		
	session.beginTransaction();
	BonLivraison p= (BonLivraison)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(BonLivraison e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<BonLivraison> findAll() {
              return session.createQuery("select c from BonLivraison c").list();
    }

 public List<BonLivraison>  findReceptionBycode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from BonLivraison c where c.numReception=:code");
		query.setParameter("code", code);
		
		return query.list();

}

     public List<BonLivraison> findFourByRechercheNomReglement(String four,String clt,String etat,String etat1,String type) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and (c.etat=:etat or c.etat =:etat1) and c.typeBon <> :type");
		query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
     
        public List<BonLivraison> findFourByRechercheNomReglementOulmes(String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
 }
     
         public List<BonLivraison> findNumCommandeByNumLivraisonOulmes(String code, String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.livraisonFour like:code and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("code","%"+code+"%");
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);

		
                return query.list();
         }
         
          public List<BonLivraison> findNumCommandeByDateLivraisonOulmes(Date dateLiv, String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.dateLivraison =:dateLiv and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("dateLiv",dateLiv);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         }
         
             public List<BonLivraison> findNumCommandeByDateLivraisonAndNumLivraisonOulmes(Date dateLiv, String code, String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.dateLivraison =:dateLiv and c.livraisonFour like:code and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("code","%"+code+"%");
                query.setParameter("dateLiv",dateLiv);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         }
             
                             public List<BonLivraison> findNumCommandeByNumFacOulmes(String fac ,String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.numFacture =:fac and (c.etat=:etat or c.etat =:etat1)");
                query.setParameter("fac",fac);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         }
             
                         public List<BonLivraison> findNumCommandeByDateLivraisonAndNumFacOulmes(Date dateLiv,String fac ,String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.dateLivraison =:dateLiv and c.numFacture =:fac and (c.etat=:etat or c.etat =:etat1)");
                query.setParameter("dateLiv",dateLiv);
                query.setParameter("fac",fac);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         }
          
            
                        public List<BonLivraison> findNumCommandeByNumLivraisonAndNumFacOulmes( String code,String fac ,String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.numFacture =:fac and c.livraisonFour like:code and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("code","%"+code+"%");
                query.setParameter("fac",fac);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         } 
             
                public List<BonLivraison> findNumCommandeByDateLivraisonAndNumLivraisonAndNumFacOulmes(Date dateLiv, String code,String fac ,String four,String clt,String etat,String etat1) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.client=:clt and c.fourisseur=:four and c.dateLivraison =:dateLiv and c.numFacture =:fac and c.livraisonFour like:code and (c.etat=:etat or c.etat =:etat1)");
		query.setParameter("code","%"+code+"%");
                query.setParameter("dateLiv",dateLiv);
                query.setParameter("fac",fac);
                query.setParameter("four",four);
                query.setParameter("clt",clt);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
		
                return query.list();
         }
             
           public BonLivraison findNumCommandeByNumLivraison(String numLivraison, String cmd  ) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.livraisonFour =:numLivraison and c.numCommande =:cmd" );
		query.setParameter("numLivraison",numLivraison);
                query.setParameter("cmd",cmd);
                
                return (BonLivraison) query.uniqueResult();
 }
           
            public List<BonLivraison> findBonLivraisonByRechercheNumLiv(String code,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.livraisonFour like:code and u.typeBon <> :type ");
		query.setParameter("code","%"+code+"%");
                query.setParameter("client",client);
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }

            public List<BonLivraison> findBonLivraisonByRechercheDateLiv(Date dateLiv,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.dateLivraison =:dateLiv and u.typeBon <> :type ");
		query.setParameter("dateLiv",dateLiv);
                query.setParameter("client",client);
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
            
                                    public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumLiv(Date dateLiv, String code ,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.dateLivraison =:dateLiv and u.livraisonFour like:code and u.typeBon <> :type ");
		query.setParameter("dateLiv",dateLiv);
                query.setParameter("code","%"+code+"%");
                query.setParameter("client",client); 
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
                 
                public List<BonLivraison> findBonLivraisonByRechercheNumFac(String fac,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.numFacture =:fac and u.typeBon <> :type ");
                query.setParameter("fac",fac);
                query.setParameter("client",client); 
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
                                    
                     public List<BonLivraison> findBonLivraisonByRechercheNumLivAndNumFac(String code , String fac,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.livraisonFour like:code and u.numFacture =:fac and u.typeBon <> :type ");
                query.setParameter("code","%"+code+"%");
                query.setParameter("fac",fac);
                query.setParameter("client",client); 
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
                                    
                    public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumFac(Date dateLiv, String fac ,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.dateLivraison =:dateLiv and u.numFacture =:fac and u.typeBon <> :type ");
		query.setParameter("dateLiv",dateLiv);
                query.setParameter("fac",fac);
                query.setParameter("client",client); 
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }                   
                                    
                        
                  public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumLivAndNumFac(Date dateLiv, String code , String fac,String client ,String four, String etat, String etat1, String type) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and (u.etat=:etat or u.etat=:etat1) and u.dateLivraison =:dateLiv and u.livraisonFour like:code and u.numFacture =:fac and u.typeBon <> :type ");
		query.setParameter("dateLiv",dateLiv);
                query.setParameter("code","%"+code+"%");
                query.setParameter("fac",fac);
                query.setParameter("client",client); 
                query.setParameter("four",four);
                query.setParameter("etat",etat);
                query.setParameter("etat1",etat1);
                query.setParameter("type",type);
		
                return query.list();
 }
            
                     public List<BonLivraison> findBonLivraisonByRechercheNumFac(String code,String client ,String four, String etat) {
		
		Query query = session.createQuery("select u from BonLivraison u where u.client=:client and u.fourisseur=:four and u.etat=:etat and u.numFacture like :code ");
		query.setParameter("code","%"+code+"%");
                query.setParameter("client",client);
                query.setParameter("four",four);
                query.setParameter("etat",etat);
               
		
                return query.list();
 }
 
                     
                           public List<BonLivraison> findFilterBonLivraisonByDateLivraisonAndFourAndClient(Date dateDebut,Date dateFin, String four, String client) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from BonLivraison c where c.dateLivraison BETWEEN :dateDebut and :dateFin and c.fourisseur =:four and c.client =:client and c.etat='Non Régler' ");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("four", four);
         query.setParameter("client", client);
         
         }
           return query.list();
    }
                           
                           
         public List<BonLivraison> findByNumFactureAndClientAndFourAndEtat(String req) {
		
		Query query = session.createQuery("select c from BonLivraison c where c.etat = 'Régler'"+req);
                return query.list();
                
 }  
        
}
