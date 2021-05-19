/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailBonLivraison;
import dao.Manager.DetailBonLivraisonDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 *
 */
public class DetailBonLivraisonDAOImpl implements DetailBonLivraisonDAO {
    Session session=HibernateUtil.openSession();


	

	public DetailBonLivraison findById(int id) {
		return (DetailBonLivraison)session.get(DetailBonLivraison.class, id);
		}

	public void add(DetailBonLivraison e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailBonLivraison edit(DetailBonLivraison e) {
		
	session.beginTransaction();
	DetailBonLivraison p= (DetailBonLivraison)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailBonLivraison e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailBonLivraison> findAll() {
		return session.createQuery("select c from DetailBonRetour c").list();
		}
	
    public List<DetailBonLivraison>  findDetaiBonLivraisonByNumLivAndNumFac(String numLiv ,String numFact) {

		Query query= session.createQuery("select c from DetailBonLivraison c where c.livraisonFour=:numLiv and c.numFacture =:numFact");
		query.setParameter("numLiv", numLiv);
                query.setParameter("numFact", numFact);
		return query.list();
}

    
        public List<DetailBonLivraison>  findDetaiBonLivraisonBycode (String code , String cmd) {

		Query query= session.createQuery("select c from DetailBonLivraison c where c.livraisonFour=:code and c.numCommande =:cmd");
		query.setParameter("code", code);
                query.setParameter("cmd", cmd);
		return query.list();
}
        
                public DetailBonLivraison  findDetaiBonLivraisonByNumlivAndNumCo (String code , String cmd) {

		Query query= session.createQuery("select c from DetailBonLivraison c where c.livraisonFour=:code and c.numCommande =:cmd");
		query.setParameter("code", code);
                query.setParameter("cmd", cmd);
		return (DetailBonLivraison) query.uniqueResult();
}
        
    public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}

    public DetailBonLivraison findDetailBonLivraisonByDetailCommande(String numCom,String numLiv, int mp) {
		
		Query query = session.createQuery("select u from DetailBonLivraison u where  u.numCommande =:numCom and u.livraisonFour =:numLiv and u.matierePremier.id =:mp ");
		query.setParameter("numCom", numCom);
		query.setParameter("numLiv", numLiv);
                query.setParameter("mp", mp);
	return (DetailBonLivraison) query.uniqueResult();
	      
				}
    
           
           public List<DetailBonLivraison> findByDetailBonLivraisonAndBl(String req) {
		
		Query query = session.createQuery("select c from DetailBonLivraison c where c.numReception like 'RCP %'"+req);
                return query.list();
                
 }
    
         public List<DetailBonLivraison> findByDetailBonLivraisonAndPf(String req) {
		
		Query query = session.createQuery("select c from DetailBonLivraison c where c.numReception like 'RCP_PF %'"+req  );
                return query.list();
                
 }  
           
               public List<Object[]> findByPrixMoyen(String req) {
        Query query=  session.createQuery("select c.matierePremier.code, c.matierePremier.nom ,sum(c.quantite), (0*c.prix) from DetailBonLivraison c where c.numReception like 'RCP %'"+req+"group by c.matierePremier");

        return query.list();
    }
           

               public List<Object[]> findByPrixMoyenAndPf(String req) {
        Query query=  session.createQuery("select c.prixOulmes.produit.code, c.prixOulmes.produit.libelle ,sum(c.quantite), (0*c.prix) from DetailBonLivraison c where c.numReception like 'RCP_PF %'"+req+"group by c.prixOulmes");

        return query.list();
    }
        
               public List<Object[]> findByAchatEmballage() {
        Query query=  session.createQuery("select c.prixOulmes ,sum(c.quantite) from DetailBonLivraison c , BonLivraison d where c.numCommande = d.numCommande and c.livraisonFour = d.livraisonFour and d.typeBon ='OLS' and YEAR(d.dateLivraison)= '2021' and c.prixOulmes in(28,29,21) GROUP BY c.prixOulmes");

        return query.list();
    }
         
               public List<Object[]> findByAchatEmballageAndClientAndFour(String client, String four) {
        Query query=  session.createQuery("select c.prixOulmes ,sum(c.quantite) from DetailBonLivraison c , BonLivraison d where c.numCommande = d.numCommande and c.livraisonFour = d.livraisonFour and d.typeBon ='OLS' and YEAR(d.dateLivraison)= '2021' and c.prixOulmes in(28,29,21) and d.client =:client and d.fourisseur =:four GROUP BY c.prixOulmes");
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }
               
                public List<Object[]> findByAchatEmballageAndMois(int prixOulmes) {
        Query query=  session.createQuery("select  MONTH(d.dateLivraison), c.prixOulmes , sum(c.quantite) from DetailBonLivraison c , BonLivraison d where c.numCommande = d.numCommande and c.livraisonFour = d.livraisonFour and d.typeBon ='OLS' and YEAR(d.dateLivraison)= '2021' and c.prixOulmes.id =:prixOulmes GROUP BY MONTH(d.dateLivraison)");
        query.setParameter("prixOulmes", prixOulmes);

        return query.list();
    }      
      
                public List<Object[]> findByAchatEmballageAndMoisAndClientAndFour(int prixOulmes,String client, String four) {
        Query query=  session.createQuery("select MONTH(d.dateLivraison), c.prixOulmes ,sum(c.quantite) from DetailBonLivraison c , BonLivraison d where c.numCommande = d.numCommande and c.livraisonFour = d.livraisonFour and d.typeBon ='OLS' and YEAR(d.dateLivraison)= '2021' and c.prixOulmes.id =:prixOulmes and d.client =:client and d.fourisseur =:four GROUP BY MONTH(d.dateLivraison)");
        query.setParameter("prixOulmes", prixOulmes);
        query.setParameter("client", client);
        query.setParameter("four", four);
        return query.list();
    }
                
               public List<Object[]> findByChiffreAffraire() {
        Query query=  session.createQuery("select MONTH(b.dateLivraison), sum(a.montant) from  DetailBonLivraison a , BonLivraison b where  a.livraisonFour= b.livraisonFour and a.numCommande = b.numCommande and b.typeBon ='OLS' and b.fourisseur ='OULMES ETAT' and YEAR(b.dateLivraison)='2021' and a.prixOulmes<> 21 and a.prixOulmes<> 28 and a.prixOulmes<> 29 group by MONTH(b.dateLivraison)");

        return query.list();
    }  
                
}