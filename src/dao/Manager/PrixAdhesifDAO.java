/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixAdhesif;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixAdhesifDAO {
      public PrixAdhesif findById(int id);
          
		public void add(PrixAdhesif prixCarton);
		
		public  PrixAdhesif edit(PrixAdhesif e);
		
		public  void delete(PrixAdhesif e); 
		
		public List<PrixAdhesif> findAll();
    
                public PrixAdhesif findDimensionByPrix(int idDim, int idFour, int idCg);
                
                public List<PrixAdhesif> findPrixAdhesifByFour(int idFour);
                 
                public List<PrixAdhesif>  findPrixAdhesifByCategorieMp(int CatMp, int Four);
                
                public PrixAdhesif findPrixAdhesifByRegion(int idDim, int idCg);
                
                public List<PrixAdhesif>  findPrixAdhesifByCategorieMpTMP(int CatMp);
    
}
