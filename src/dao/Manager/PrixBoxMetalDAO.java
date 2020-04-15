/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBoxMetal;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixBoxMetalDAO {
      public PrixBoxMetal findById(int id);
          
		public void add(PrixBoxMetal prixBoxMetal);
		
		public  PrixBoxMetal edit(PrixBoxMetal e);
		
		public  void delete(PrixBoxMetal e); 
		
		public List<PrixBoxMetal> findAll();
    
                public PrixBoxMetal findDimensionByPrixBoxMetal( int idFour, int idCg);
                
                public List<PrixBoxMetal> findPrixBoxMetalByFour(int idFour);
                
}
