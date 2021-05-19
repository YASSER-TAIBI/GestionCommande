/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.GrammageFilm;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Hp
 */
public interface GrammageFilmDAO {
      public GrammageFilm findById(int id);
          
		public void add(GrammageFilm grammageFilm);
		
		
		public  GrammageFilm edit(GrammageFilm e);
		
		public  void delete(GrammageFilm e); 
		
		public List<GrammageFilm> findAll();
                
        	public List<GrammageFilm> findByCodeGrammageFilm(String code);
	
         	public List<GrammageFilm> findBylibelleGrammageFilm(String lib);
    
}
