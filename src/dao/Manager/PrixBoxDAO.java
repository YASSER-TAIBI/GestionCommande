/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixBox;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixBoxDAO {
    
      public PrixBox findById(int id);
          
		public void add(PrixBox prixBox);
		
		public  PrixBox edit(PrixBox e);
		
		public  void delete(PrixBox e); 
		
		public List<PrixBox> findAll();
                
                public PrixBox findDimensionByPrixBox(int idDim, int idFour, int idCg, int idGram, int idTcb,int idInt);
                
                public PrixBox findPrixBoxByRegion(int idDim, int idCg, int idGram, int idTcb,int idInt);
                  
                public List<PrixBox> findPrixBoxByFour(int idFour);
                
                public List<PrixBox>  findPrixBoxByCategorieMp(int CatMp , int Four);
                
                public List<PrixBox>  findPrixBoxByCategorieMpTMP(int CatMp);
}
