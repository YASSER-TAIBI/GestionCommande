/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailBonRetour;
import dao.Manager.DetailBonRetourDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailBonRetourDAOImpl implements DetailBonRetourDAO {
    Session session=HibernateUtil.openSession();



	public DetailBonRetour findById(int id) {
		return (DetailBonRetour)session.get(DetailBonRetour.class, id);
		}

	public void add(DetailBonRetour detailBonRetour) {
		session.beginTransaction();
		session.save(detailBonRetour);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailBonRetour edit(DetailBonRetour e) {
		
	session.beginTransaction();
	DetailBonRetour p= (DetailBonRetour)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailBonRetour e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
	public List<DetailBonRetour> findAll() {
		return session.createQuery("select c from DetailBonRetour c").list();
		}
	

	    public List<DetailBonRetour>  findDetailBonRetourByBonRetour (int cmd) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.bonRetour.id=:cmd");
		query.setParameter("cmd", cmd);
		
		return query.list();
}

            	    public List<DetailBonRetour>  findDetailBonRetourByMpAndFourAndClient (String type, String req) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.bonRetour.type=:type"+req );
		query.setParameter("type", type);
                
		return query.list();
}
            
//                       	    public List<DetailBonRetour>  findDetailBonRetourByListFour (String four, String type) {
//
//		Query query= session.createQuery("select c from DetailBonRetour c where c.bonRetour.fournisseur=:four and c.bonRetour.type=:type");
//		query.setParameter("four", four);
//		query.setParameter("type", type);
//                
//		return query.list();
//}
//            
//                            public List<DetailBonRetour>  findDetailBonRetourByFourAndMp (String mp ,String four,String type ) {
//
//		Query query= session.createQuery("select c from DetailBonRetour c where c.matierePremier.code=:mp and c.bonRetour.fournisseur=:four and c.bonRetour.type=:type");
//		query.setParameter("four", four);
//		query.setParameter("mp", mp);
//                query.setParameter("type", type);
//                
//		return query.list();
//}
                            
            	    public List<DetailBonRetour>  findDetailBonRetourByType (String type) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.bonRetour.type=:type");
		query.setParameter("type", type);
		
		return query.list();
}
            
     public DetailBonRetour findDetailBonRetourByDetailBonLivraison(String numCom,String numLiv, int mp) {
		
		Query query = session.createQuery("select u from DetailBonRetour u where u.bonRetour.numCommande =:numCom and u.bonRetour.numLivraison =:numLiv and u.matierePremier.id =:mp ");
		query.setParameter("numCom", numCom);
		query.setParameter("numLiv", numLiv);
                query.setParameter("mp", mp);
	return (DetailBonRetour) query.uniqueResult();
	      
				}

    
        public List<Object[]> findBySituationRetourManque() {
            
              Query query=  session.createQuery("select a.matierePremier,"
                      + "(select COALESCE(SUM(b.quantiteRetour),0) from DetailBonRetour b where b.bonRetour.type= 'Retour' and b.bonRetour.statut = 'Mp' and b.matierePremier= a.matierePremier), "
                      + "(select COALESCE(SUM(b.quantiteRetour),0) from DetailBonRetour b where b.bonRetour.type= 'Manque' and b.bonRetour.statut = 'Mp' and b.matierePremier= a.matierePremier),"
                      + "(select COALESCE(SUM(b.montant),0)  from DetailBonRetour b where b.bonRetour.type= 'Retour' and b.bonRetour.statut = 'Mp' and b.matierePremier= a.matierePremier),"
                      + "(select COALESCE(SUM(b.montant),0) from DetailBonRetour b where b.bonRetour.type= 'Manque' and b.bonRetour.statut = 'Mp' and b.matierePremier= a.matierePremier) "
                      + "from DetailBonRetour a GROUP BY a.matierePremier");

        return query.list();
    }
           
          public List<Object[]> findBySituationRetourManquePF() {
            
              Query query=  session.createQuery("select a.prixOulmes,"
                      + "(select COALESCE(SUM(b.quantiteRetour),0) from DetailBonRetour b where b.bonRetour.type= 'Retour' and b.bonRetour.statut = 'Pf' and b.prixOulmes= a.prixOulmes), "
                      + "(select COALESCE(SUM(b.quantiteRetour),0) from DetailBonRetour b where b.bonRetour.type= 'Manque' and b.bonRetour.statut = 'Pf' and b.prixOulmes= a.prixOulmes),"
                      + "(select COALESCE(SUM(b.montant),0)  from DetailBonRetour b where b.bonRetour.type= 'Retour' and b.bonRetour.statut = 'Pf' and b.prixOulmes= a.prixOulmes),"
                      + "(select COALESCE(SUM(b.montant),0) from DetailBonRetour b where b.bonRetour.type= 'Manque' and b.bonRetour.statut = 'Pf' and b.prixOulmes= a.prixOulmes) "
                      + "from DetailBonRetour a GROUP BY a.prixOulmes");

        return query.list();
    }
        
          public List<DetailBonRetour>  findByMatierePremier (int mp) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.matierePremier.id =:mp AND (c.bonRetour.type= 'Manque' OR c.bonRetour.type= 'Retour')");
		query.setParameter("mp", mp);
		
		return query.list();
}
          
            public List<DetailBonRetour>  findByMatierePremierAndClient (int mp, String client ) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.matierePremier.id =:mp AND c.bonRetour.client =:client AND (c.bonRetour.type= 'Manque' OR c.bonRetour.type= 'Retour')");
		query.setParameter("mp", mp);
		query.setParameter("client", client);
                
		return query.list();
}
          
                   public List<DetailBonRetour>  findByPrixOulmes (int pf) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.prixOulmes.id =:pf AND (c.bonRetour.type= 'Manque' OR c.bonRetour.type= 'Retour')");
		query.setParameter("pf", pf);
		
		return query.list();
}    
                   
                   public List<DetailBonRetour>  findByPrixOulmesAndClient (int pf, String client) {

		Query query= session.createQuery("select c from DetailBonRetour c where c.prixOulmes.id =:pf AND c.bonRetour.client =:client AND (c.bonRetour.type= 'Manque' OR c.bonRetour.type= 'Retour')");
		query.setParameter("pf", pf);
		query.setParameter("client", client);
                
		return query.list();
}    
}
