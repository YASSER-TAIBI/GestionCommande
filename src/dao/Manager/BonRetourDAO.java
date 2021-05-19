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

		
		public BonRetour findById(int id);
		
		public void add(BonRetour bonRetour);
		
		public  BonRetour edit(BonRetour e);
		
		public  void delete(BonRetour e); 
		
		public List<BonRetour> findAll();
                  
                public List<BonRetour> findTypeByRechercheBonRetourMP(String typ, String typ1);

                public List<BonRetour> findTypeByRechercheBonRetourPF(String typ, String typ1);
                
                 public List<BonRetour> findTypeByRechercheBonRetour(String typ, String typ1);
                
                public BonRetour findBonRetourByNumCommAndNumLiv(String nCom ,String nRtr, String etat);

                public List<BonRetour> findBonRetourByRechercheFourAndClient(String four ,String client, String typ, String typ1); 
   
                public List<BonRetour> findBonRetourByFilterMP(String typ, String typ1 , String req);
                
                public List<BonRetour> findBonRetourByFilterPF(String typ, String typ1 , String req);
}
