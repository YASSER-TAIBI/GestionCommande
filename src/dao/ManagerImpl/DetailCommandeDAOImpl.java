/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.DetailCommande;
import dao.Manager.DetailCommandeDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Commande;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author h
 */
public class DetailCommandeDAOImpl implements DetailCommandeDAO {
    Session session=HibernateUtil.openSession();


	public DetailCommande findUtilisateurByLoginMotPasse(String login,
			String motPasse) {
		
		Query query = session.createQuery("select u from Utilisateur u where  u.login=:login and u.password=:motPasse");
		query.setParameter("login", login);
		query.setParameter("motPasse", motPasse);
	return (DetailCommande) query.uniqueResult();
	      
				}

	public DetailCommande findById(int id) {
		return (DetailCommande)session.get(DetailCommande.class, id);
		}

	public void add(DetailCommande utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	
	public DetailCommande edit(DetailCommande e) {
		
	session.beginTransaction();
	DetailCommande p= (DetailCommande)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCommande e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		      
	}

        
	
        public List<DetailCommande> findByEtat(String etat) {
		Query query = session.createQuery("select c from DetailCommande c where c.etat=:etat");
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }



public List<DetailCommande> findCommanByProduit(int produit ,String etat ) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.matierePremier.categorieMp.subCategorieMp.id =:produit and u.etat =:etat");
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
                return query.list() ;
        }

           public List<DetailCommande> findDetailCommandeByFournisseur(int idFour, String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.commande.fourisseur.id =:idFour and c.etat =:etat");
		query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
		
                return query.list();
 }
           
             public List<DetailCommande> findDetailCommandeByMp(String Mp, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.matierePremier.code =:Mp and u.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("etat", etat);
               
		
                  return query.list();
 }
             
                   public List<DetailCommande> findFilterDetailCommandeByDateCommande(Date dateDebut,Date dateFin,String etat) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.etat =:etat");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("etat", etat);
         }
         
           return query.list();
    }
                      public List<DetailCommande> findDetailCommandeByEtat(Commande commande, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.commande =:commande and u.etat=:etat");
		query.setParameter("commande",commande);
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }
                      
                             public List<DetailCommande> findDetailNumCommandeByEtat(String Numcom, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.commande.nCommande =:Numcom and u.etat=:etat");
		query.setParameter("Numcom",Numcom);
                query.setParameter("etat",etat);
               
		
                  return query.list();
 }
                      
            public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndProduit(Date dateDebut,Date dateFin,int produit,String etat) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("produit", produit);
         query.setParameter("etat", etat);
         }
           return query.list();
                             }
                             
            public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndFour(Date dateDebut,Date dateFin,int idFour,String etat) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.commande.fourisseur.id =:idFour and c.etat =:etat");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("idFour", idFour);
         query.setParameter("etat", etat);
         }
           return query.list();
                             }
            
               public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorie(int idFour, int produit,String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.commande.fourisseur.id =:idFour and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("idFour",idFour);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
		
                return query.list();
 }
               
                    public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorieAndDate(Date dateDebut, Date dateFin ,int idFour, int produit ,String etat) {
		
                          Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		 query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.commande.fourisseur.id =:idFour and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("idFour",idFour);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                     
                    public List<DetailCommande> findDetailCommandeByMpAndDate(Date dateDebut, Date dateFin , String Mp ,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.code =:Mp and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("Mp", Mp);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                         public List<DetailCommande> findDetailCommandeByMpAndSubCategorie(String Mp , int produit,String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
		
                return query.list();
 }
                         
                public List<DetailCommande> findDetailCommandeByMpAndDateAndSubCategorie(Date dateDebut, Date dateFin , String Mp , int produit,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.code =:Mp and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("produit", produit);
                query.setParameter("Mp",Mp);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                
                                    public List<DetailCommande> findDetailCommandeByMpAndFour(String Mp ,int idFour,String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.commande.fourisseur.id =:idFour and c.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
		
                return query.list();
 }
                                    
               public List<DetailCommande> findDetailCommandeByMpAndDateAndFour(Date dateDebut, Date dateFin , String Mp , int idFour,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.code =:Mp and c.commande.fourisseur.id =:idFour and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("idFour",idFour);
                query.setParameter("Mp",Mp);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                 public List<DetailCommande> findDetailCommandeByMpAndFourAndSubCategorie(String Mp ,int idFour,int produit,String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.commande.fourisseur.id =:idFour and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("idFour",idFour);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
		
                return query.list();
 }  
                        public List<DetailCommande> findDetailCommandeByMpAndDateAndFourAndSubCategorie(Date dateDebut, Date dateFin , String Mp ,int produit ,int idFour,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.code =:Mp and c.commande.fourisseur.id =:idFour and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("idFour",idFour);
                query.setParameter("Mp",Mp);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                        
//           public List<Object[]> findDetailCommandeByQteRestee(int depot, String etat, int util, String magasin ) {
//		
//		Query query = session.createQuery("select sum(u.quantiteRestee),u.matierePremier.nom from DetailCommande u where u.commande.depot.id =:depot and u.etat =:etat and u.utilisateurCreation.id =:util and u.magasin =: magasin group by u.matierePremier having qte <> 0");
//                query.setParameter("depot", depot);
//                query.setParameter("etat", etat);
//                query.setParameter("util", util);
//                query.setParameter("magasin", magasin);
//	    return query.list();
//	      
//				}      
               
             public List<DetailCommande> findDetailCommandeByMpAndEtat(int mp, String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.quantiteRestee <> 0 and  u.matierePremier.id =:mp and u.etat =:etat ");
		query.setParameter("mp", mp);
                query.setParameter("etat", etat);
	    return query.list();
	      
				}    
             
                         public DetailCommande findDetailCommandeByBonRetour(String Mp ,String idFour,String etat,String nCom) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.commande.fourisseur.nom =:idFour and c.etat =:etat and c.commande.nCommande =:nCom");
		query.setParameter("Mp",Mp);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
		query.setParameter("nCom", nCom);

                return (DetailCommande)  query.uniqueResult();
                         }
                         
                         public List<DetailCommande> findCommanByEtatCmd(String etatCmd ,String etat ) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.commande.etat =:etatCmd and u.etat =:etat");
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("etat", etat);
                return query.list() ;
        }
                         
                        public List<DetailCommande> findDetailCommandeBySubCategorieAndEtatCmd(int produit,String etatCmd ,String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.etat =:etatCmd and c.etat =:etat");
                query.setParameter("produit", produit);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("etat", etat);
		
                return query.list();
 }
                           public List<DetailCommande> findDetailCommandeByFournisseurAndEtatCmd(int idFour ,String etatCmd , String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.commande.fourisseur.id =:idFour and c.commande.etat =:etatCmd and c.etat =:etat");
		query.setParameter("idFour",idFour);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("etat", etat);
		
                return query.list();
 }       
           
                               public List<DetailCommande> findDetailCommandeByMpAndEtatCmd(String Mp ,String etatCmd , String etat) {
		
		Query query = session.createQuery("select u from DetailCommande u where u.matierePremier.code =:Mp and u.commande.etat =:etatCmd and u.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("etat", etat);
               
		
                  return query.list();
 }
                               
              public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndEtatCmd(Date dateDebut,Date dateFin, String etatCmd ,String etat) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
        
         query= session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and u.commande.etat =:etatCmd and c.etat =:etat");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         query.setParameter("etatCmd", etatCmd);
         query.setParameter("etat", etat);
         }
         
           return query.list();
    }                   


           public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorieAndEtatCmd(int produit,int idFour ,String etatCmd , String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.fourisseur.id =:idFour and c.commande.etat =:etatCmd and c.etat =:etat");
		query.setParameter("idFour",idFour);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
		
                return query.list();
 }  
              
                public List<DetailCommande> findDetailCommandeByMpAndSubCategorieAndEtatCmd(int produit, String Mp ,String etatCmd , String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where c.matierePremier.code =:Mp and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.etat =:etatCmd and c.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
		
                return query.list();
 }
                
                public List<DetailCommande> findDetailCommandeByDateAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit ,String etatCmd ,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.etat =:etatCmd and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                
                  public List<DetailCommande> findDetailCommandeByDateAndFourAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit, int idFour  ,String etatCmd ,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.etat =:etatCmd and c.commande.fourisseur.id =:idFour and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
         }
                return query.list();
 }
                              public List<DetailCommande> findDetailCommandeByMpAndSubCategorieAndFourAndEtatCmd(int produit, int idFour , String Mp ,String etatCmd , String etat) {
		
		Query query = session.createQuery("select c from DetailCommande c where u.matierePremier.code =:Mp and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.fourisseur.id =:idFour and c.commande.etat =:etatCmd and c.etat =:etat");
		query.setParameter("Mp",Mp);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
		
                return query.list();
 }
                              
                        public List<DetailCommande> findDetailCommandeByMpAndDateAndFourAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit, String Mp, int idFour  ,String etatCmd ,String etat) {
		
               Query query= null;
         
         if (dateDebut!=null && dateFin !=null){
                        
		query = session.createQuery("select c from DetailCommande c where c.commande.dateCreation BETWEEN :dateDebut and :dateFin and c.matierePremier.code =:Mp and c.matierePremier.categorieMp.subCategorieMp.id =:produit and c.commande.etat =:etatCmd and c.commande.fourisseur.id =:idFour and c.etat =:etat");
		query.setParameter("dateDebut", dateDebut);
                query.setParameter("dateFin", dateFin);
                query.setParameter("etatCmd", etatCmd);
                query.setParameter("produit", produit);
                query.setParameter("idFour",idFour);
                query.setParameter("etat", etat);
                query.setParameter("Mp",Mp);
         }
                return query.list();
 }
}
