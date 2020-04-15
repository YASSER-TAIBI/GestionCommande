/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixCarton;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixCartonDAO {
    
       public PrixCarton findById(int id);
          
		public void add(PrixCarton prixCarton);
		
		public  PrixCarton edit(PrixCarton e);
		
		public  void delete(PrixCarton e); 
		
		public List<PrixCarton> findAll();
                
                public List<PrixCarton> findPrixCartonByFour(int idFour);
 
                public PrixCarton findDimensionByPrixCarton(int idDim, int idFour, int idCg, int idTcb,int idInt);
                
                public PrixCarton findPrixCartonByRegion(int idDim, int idCg, int idTcb,int idInt);
                 
                public List<PrixCarton>  findPrixCartonByCategorieMp(int CatMp, int Four);
                
                public List<PrixCarton>  findPrixCartonByCategorieMpTMP(int CatMp);
}
