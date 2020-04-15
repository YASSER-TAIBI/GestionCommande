/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailBonRetour;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailBonRetourDAO {
      public  DetailBonRetour findUtilisateurByLoginMotPasse(String login,String motPasse);
		
		public DetailBonRetour findById(int id);
		
		public void add(DetailBonRetour detailBonRetour);

		public  DetailBonRetour edit(DetailBonRetour e);
		
		public  void delete(DetailBonRetour e); 
		
		public List<DetailBonRetour> findAll();
                
                public List<DetailBonRetour>  findDetailBonRetourByBonRetour (int cmd);
                
                public DetailBonRetour findDetailBonRetourByDetailBonLivraison(String numCom,String numLiv, int mp);
                
                public List<Object[]>findDetailBonRetourByMpManque();
                 
                public List<Object[]>findDetailBonRetourByManque() ;
                
                public List<Object[]>findDetailBonRetourByCodeMp(String codeMp);
                  
                public List<Object[]>findDetailBonRetourByNmanque(String nManque);
                    
                public List<Object[]>findDetailBonRetourByDate(Date dateD ,Date dateF);
                      
                public List<Object[]>findDetailBonRetourByEtat(String etat);
                
                 public List<Object[]>findDetailBonRetourByFour(String four );
                        
                public List<Object[]>findDetailBonRetourByCodeAndNmnq(String code,String nMnq );
                         
                public List<Object[]>findDetailBonRetourByCodeAndDate(String code,Date dateD,Date dateF);
                
                public List<Object[]>findDetailBonRetourByCodeAndEtat(String code, String etat);
                    
                public List<Object[]>findDetailBonRetourByCodeAndFour(String code, String four);
                         
                public List<Object[]>findDetailBonRetourByManqueAndDate(String manque,Date dateD,Date dateF);

                public List<Object[]>findDetailBonRetourByManqueAndEtat(String manque, String etat);

                public List<Object[]>findDetailBonRetourByManqueAndFour(String manque, String four);  
                
                public List<Object[]>findDetailBonRetourByDateAndEtat(Date dateD,Date dateF,String etat);
                
                public List<Object[]>findDetailBonRetourByDateAndFour(Date dateD,Date dateF,String four);

                public List<Object[]>findDetailBonRetourByEtatAndFour(String etat, String four);
                
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndDate(String code,String manque,Date dateD,Date dateF);
                
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndEtat(String code,String manque,String etat) ;
      
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndFour(String code,String manque,String four);

                public List<Object[]>findDetailBonRetourByCodeAndDateAndEtat(String code,Date dateD,Date dateF,String etat);

                public List<Object[]>findDetailBonRetourByCodeAndDateAndFour(String code,Date dateD,Date dateF,String four);
                
                public List<Object[]>findDetailBonRetourByManqueAndDateAndEtat(String manque,Date dateD,Date dateF,String etat);
                
                public List<Object[]>findDetailBonRetourByManqueAndDateAndFour(String manque,Date dateD,Date dateF,String four);
                
                public List<Object[]>findDetailBonRetourByManqueAndEtatAndFour(String manque,String etat,String four);

                public List<Object[]>findDetailBonRetourByDateAndEtatAndFour(Date dateD,Date dateF, String etat, String four);
                
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndEtat(String code,String manque ,Date dateD,Date dateF, String etat) ;
                 
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndFour(String code,String manque ,Date dateD,Date dateF, String four) ;
                
                public List<Object[]>findDetailBonRetourByCodeAndDateAndEtatAndFour(String code,Date dateD,Date dateF, String etat, String four) ;
                              
                public List<Object[]>findDetailBonRetourByCodeAndEtatAndFourAndManque(String code, String etat, String four,String manque) ;
                               
                public List<Object[]>findDetailBonRetourByManqueAndDateAndEtatAndFour(String manque,Date dateD,Date dateF, String etat, String four) ;
                       
                public List<Object[]>findDetailBonRetourByCodeAndManqueAndDateAndEtatAndFour(String code,String manque,Date dateD,Date dateF, String etat, String four);
}
