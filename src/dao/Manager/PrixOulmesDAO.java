/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixOulmes;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixOulmesDAO {
    
      public PrixOulmes findById(int id);
          
		public void add(PrixOulmes prixBox);
		
		public  PrixOulmes edit(PrixOulmes e);
		
		public  void delete(PrixOulmes e); 
		
		public List<PrixOulmes> findAll();
                
                public PrixOulmes findPrixOulmesByFourAndArt( String Four, int idArt , String client , String lieu);
                
                public PrixOulmes findPrixOulmesByCodeArt( String codeArt , String client , String lieu);
                 
                public PrixOulmes findPrixOulmesByCodeArt(String client );
                 
                public List<PrixOulmes> findPrixFilmByFour(String Four);
                 
                public List<PrixOulmes> findPrixFilmByClient(String client) ;
                
                public List<String> findPrixOulmesByClient(String client);
                 
                  public List<PrixOulmes> findPrixOulmesByLieu(String lieu);
}
