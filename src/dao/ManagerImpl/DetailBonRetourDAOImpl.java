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


	public DetailBonRetour findUtilisateurByLoginMotPasse(String login,
			String motPasse) {
		
		Query query = session.createQuery("select u from Utilisateur u where  u.login=:login and u.password=:motPasse");
		query.setParameter("login", login);
		query.setParameter("motPasse", motPasse);
	return (DetailBonRetour) query.uniqueResult();
	      
				}

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

     public DetailBonRetour findDetailBonRetourByDetailBonLivraison(String numCom,String numLiv, int mp) {
		
		Query query = session.createQuery("select u from DetailBonRetour u where  u.bonRetour.numCommande =:numCom and u.bonRetour.numLivraison =:numLiv and u.matierePremier.id =:mp ");
		query.setParameter("numCom", numCom);
		query.setParameter("numLiv", numLiv);
                query.setParameter("mp", mp);
	return (DetailBonRetour) query.uniqueResult();
	      
				}

    
     	    public List<Object[]>findDetailBonRetourByMpManque() {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant)  from DetailBonRetour c where c.bonRetour.type= 'Manque' GROUP BY c.matierePremier.id");
		
		return query.list();
}
     
            public List<Object[]>findDetailBonRetourByManque() {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' GROUP BY c.matierePremier.id");
		
		return query.list();
}
            
                    public List<Object[]>findDetailBonRetourByCodeMp(String codeMp ) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.matierePremier.code =:codeMp GROUP BY c.matierePremier.id");
		
                query.setParameter("codeMp", codeMp);
                
		return query.list();
}
               public List<Object[]>findDetailBonRetourByNmanque(String nManque ) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour =:nManque GROUP BY c.matierePremier.id");
		
                query.setParameter("nManque", nManque);
                
		return query.list();
}
            
                public List<Object[]>findDetailBonRetourByDate(Date dateD ,Date dateF ) {
                    
            Query query= null;
         
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
}
                
                    public List<Object[]>findDetailBonRetourByEtat(String etat ) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat =:etat GROUP BY c.matierePremier.id");
		
                query.setParameter("etat", etat);
                
		return query.list();
}
                    
                    public List<Object[]>findDetailBonRetourByFour(String four ) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.fournisseur =:four GROUP BY c.matierePremier.id");
		
                query.setParameter("four", four);
                
		return query.list();
}
                    
                          public List<Object[]>findDetailBonRetourByCodeAndNmnq(String code,String nMnq ) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.matierePremier.code=:code and c.bonRetour.numRetour=:nMnq GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("nMnq", nMnq);
                
		return query.list();
                
}
                          
                    public List<Object[]>findDetailBonRetourByCodeAndDate(String code,Date dateD,Date dateF) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.matierePremier.code=:code and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                    
                       public List<Object[]>findDetailBonRetourByCodeAndEtat(String code, String etat) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.matierePremier.code=:code and c.bonRetour.etat=:etat GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("etat", etat);
                
		return query.list();
                
}
                                   public List<Object[]>findDetailBonRetourByCodeAndFour(String code, String four) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("four", four);
                
		return query.list();
                
}
                       public List<Object[]>findDetailBonRetourByManqueAndDate(String manque,Date dateD,Date dateF) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                    public List<Object[]>findDetailBonRetourByManqueAndEtat(String manque, String etat) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.bonRetour.etat=:etat GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                
		return query.list();
                
}
            public List<Object[]>findDetailBonRetourByManqueAndFour(String manque, String four) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.bonRetour.fournisseur=:four GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("four", four);
                
		return query.list();
                
}         
                 public List<Object[]>findDetailBonRetourByDateAndEtat(Date dateD,Date dateF,String etat) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("etat", etat);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                   public List<Object[]>findDetailBonRetourByDateAndFour(Date dateD,Date dateF,String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.fournisseur=:four and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                              public List<Object[]>findDetailBonRetourByEtatAndFour(String etat, String four) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.bonRetour.fournisseur=:four GROUP BY c.matierePremier.id");
		
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                
		return query.list();
                
}  
                               public List<Object[]>findDetailBonRetourByCodeAndManqueAndDate(String code,String manque,Date dateD,Date dateF) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                               
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndEtat(String code,String manque,String etat) {
             
		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.etat=:etat GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                
         
		return query.list();
                
}
                
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndFour(String code,String manque,String four) {
             
		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("four", four);
                
         
		return query.list();
                
}
                 public List<Object[]>findDetailBonRetourByCodeAndDateAndEtat(String code,Date dateD,Date dateF,String etat) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.matierePremier.code=:code and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("etat", etat);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                                public List<Object[]>findDetailBonRetourByCodeAndDateAndFour(String code,Date dateD,Date dateF,String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.fournisseur=:four and c.matierePremier.code=:code and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                              public List<Object[]>findDetailBonRetourByManqueAndDateAndEtat(String manque,Date dateD,Date dateF,String etat) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.bonRetour.numRetour=:manque and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}    
                    public List<Object[]>findDetailBonRetourByManqueAndDateAndFour(String manque,Date dateD,Date dateF,String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.fournisseur=:four and c.bonRetour.numRetour=:manque and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                              public List<Object[]>findDetailBonRetourByManqueAndEtatAndFour(String manque,String etat,String four) {
             
		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.bonRetour.etat=:etat and c.bonRetour.fournisseur=:four GROUP BY c.matierePremier.id");
		
                query.setParameter("etat", etat);
                query.setParameter("manque", manque);
                query.setParameter("four", four);
                
         
		return query.list();
                
}
                                   public List<Object[]>findDetailBonRetourByDateAndEtatAndFour(Date dateD,Date dateF, String etat, String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.fournisseur=:four and c.bonRetour.etat=:etat and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
               public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndEtat(String code,String manque ,Date dateD,Date dateF, String etat) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.etat=:etat and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
               
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndFour(String code,String manque ,Date dateD,Date dateF, String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                              public List<Object[]>findDetailBonRetourByCodeAndDateAndEtatAndFour(String code,Date dateD,Date dateF, String etat, String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}
                               public List<Object[]>findDetailBonRetourByCodeAndEtatAndFourAndManque(String code, String etat, String four,String manque) {

		Query query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four and c.bonRetour.numRetour=:manque GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                query.setParameter("manque", manque);

   
		return query.list();
                
}                   
                       public List<Object[]>findDetailBonRetourByManqueAndDateAndEtatAndFour(String manque,Date dateD,Date dateF, String etat, String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.bonRetour.numRetour=:manque and c.bonRetour.fournisseur=:four and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}         
                     public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndEtatAndFour(String code,String manque,Date dateD,Date dateF, String etat, String four) {

                  Query query= null;
                  
         if (dateD!=null && dateF !=null){
             
		 query= session.createQuery("select c.matierePremier  ,sum(c.montant), c.bonRetour.dateCreation, c.bonRetour.numRetour, c.bonRetour.fournisseur, c.bonRetour.etat from DetailBonRetour c where c.bonRetour.type= 'Manque' and c.bonRetour.etat=:etat and c.bonRetour.numRetour=:manque and c.matierePremier.code=:code and c.bonRetour.fournisseur=:four and c.bonRetour.dateCreation BETWEEN :dateD and :dateF GROUP BY c.matierePremier.id");
		
                query.setParameter("code", code);
                query.setParameter("manque", manque);
                query.setParameter("etat", etat);
                query.setParameter("four", four);
                query.setParameter("dateD", dateD);
                query.setParameter("dateF", dateF);
         }
		return query.list();
                
}   
                       
}
