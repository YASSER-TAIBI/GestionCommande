package dao.Manager;

import dao.Entity.BonLivraison;
import dao.Entity.Fournisseur;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public interface BonLivraisonDAO {
    
    public BonLivraison findById(int id);
		
		public void add(BonLivraison bonLivraison);
		
		public  BonLivraison edit(BonLivraison e);
		
		public  void delete(BonLivraison e); 
		
		public List<BonLivraison> findAll();
                
                public List<BonLivraison> findBonLivraisonByEtat(String etat) ;
                
                public List<BonLivraison> findReceptionBycode(String code);
                
                public List<BonLivraison> findFourByRechercheNomReglement(String four,String clt,String etat,String etat1,String type);
               
                public List<BonLivraison> findFourByRechercheNomReglementOulmes(String four,String clt,String etat,String etat1,String type,String type1);

//              public List<BonLivraison> findCommandeByNumLivraison(String ncom );
               
                public BonLivraison findNumCommandeByNumLivraison(String numLivraison, String cmd) ;
                
                public List<BonLivraison> findBonLivraisonByRechercheNumLiv(String code,String client ,String four, String etat, String etat1, String type) ;
               
                public List<BonLivraison> findBonLivraisonByRechercheNumFac(String code,String client ,String four, String etat);
                
                public List<BonLivraison> findBonLivraisonByRechercheDateLiv(Date dateLiv,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumLiv(Date dateLiv, String code ,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findNumCommandeByNumLivraisonOulmes(String code, String four,String clt,String etat,String etat1,String type,String type1);
                  
                public List<BonLivraison> findNumCommandeByDateLivraisonOulmes(Date dateLiv, String four,String clt,String etat,String etat1,String type,String type1);
                   
                public List<BonLivraison> findNumCommandeByDateLivraisonAndNumLivraisonOulmes(Date dateLiv, String code, String four,String clt,String etat,String etat1,String type,String type1);
                
                public List<BonLivraison> findBonLivraisonByRechercheNumFac(String fac,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findBonLivraisonByRechercheNumLivAndNumFac(String code , String fac,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumFac(Date dateLiv, String fac ,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findBonLivraisonByRechercheDateLivAndNumLivAndNumFac(Date dateLiv, String code , String fac,String client ,String four, String etat, String etat1, String type);
                
                public List<BonLivraison> findNumCommandeByNumFacOulmes(String fac ,String four,String clt,String etat,String etat1,String type,String type1);
                
                public List<BonLivraison> findNumCommandeByDateLivraisonAndNumFacOulmes(Date dateLiv,String fac ,String four,String clt,String etat,String etat1,String type,String type1);

                public List<BonLivraison> findNumCommandeByNumLivraisonAndNumFacOulmes( String code,String fac ,String four,String clt,String etat,String etat1,String type,String type1);

                public List<BonLivraison> findNumCommandeByDateLivraisonAndNumLivraisonAndNumFacOulmes(Date dateLiv, String code,String fac ,String four,String clt,String etat,String etat1,String type,String type1);

                  public List<BonLivraison> findFilterBonLivraisonByDateLivraisonAndFourAndClient(Date dateDebut,Date dateFin, String four, String client);

}