/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.TypeFilm;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TypeFilmDAO {
    

     public TypeFilm findById(int id);
          
		public void add(TypeFilm typeFilm);
		
		
		public  TypeFilm edit(TypeFilm e);
		
		public  void delete(TypeFilm e); 
		
		public List<TypeFilm> findAll();
    
    
}

