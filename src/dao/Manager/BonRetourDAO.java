/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.BonRetour;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface BonRetourDAO {
      public int getMaxId();
		
		public BonRetour findById(int id);
		
		public void add(BonRetour bonRetour);
		
		public  BonRetour edit(BonRetour e);
		
		public  void delete(BonRetour e); 
		
		public List<BonRetour> findAll();
                           
                public List<BonRetour> findEtatByRechercheBonRetour(String etat);
                                      
                public List<BonRetour> findTypeByRechercheBonRetour(String typ, String typ1);
                
                public BonRetour findBonRetourByNumRetour(String nRetour ,String etat);
                  
                public BonRetour findBonRetourByNumCommAndNumLiv(String nCom ,String nRtr, String etat, String resUsi);
                
                public List<BonRetour> findBonRetourByFourAndStockAndRec(String recUsi, String stock, String four,String retMaq);
                
                public List<BonRetour> findBonRetourByFourAndRec(String recUsi, String four,String retMaq);

                public List<BonRetour> findBonRetourByRechercheFourAndClient(String four ,String client, String typ, String typ1); 
                
                public List<BonRetour> findBonRetour(String retMaq);
                
                public List<BonRetour> findBonRetourByFour(String four,String retMaq);
                
                public List<BonRetour> findBonRetourByRecUsine(String recUsi,String retMaq);
                
                public List<BonRetour> findBonRetourByStock(String stock,String retMaq);
                
                public List<BonRetour> findByFourAndRec(String recUsi, String four);
                 
                public List<BonRetour> findByFour(String four);
                
                public List<BonRetour> findBonRetourByEtatAndRetMnq(String retMaq ,String etat) ;
                
                public List<BonRetour> findBonRetourByFourAndStockAndRecAndEtat(String recUsi, String stock, String four,String retMaq,String etat);
                
                public List<BonRetour> findBonRetourByFourAndRecAndEtat(String recUsi, String four, String retMaq, String etat);
                
                public List<BonRetour> findBonRetourByEtatAndFour(String four ,String etat);
                
                public List<BonRetour> findBonRetourByEtatAndResUsi(String resUsi ,String etat);
                
                public List<BonRetour> findBonRetourByEtatAndResUsiAndFour(String resUsi ,String etat, String four);
                
                
}
