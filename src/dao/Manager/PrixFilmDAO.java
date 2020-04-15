/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixFilm;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixFilmDAO {
    
        public PrixFilm findById(int id);
          
		public void add(PrixFilm PrixFilm);
		
		public  PrixFilm edit(PrixFilm e);
		
		public  void delete(PrixFilm e); 
		
		public List<PrixFilm> findAll();

                public PrixFilm findDimensionByPrixFilm(int idFour, int idTfilm, int idGfilm,int idCg,int idInt);
                
                public List<PrixFilm> findPrixFilmByFour(int idFour);
    
                public List<PrixFilm>  findPrixFilmByCategorieMp(int CatMp , int Four);
                
                public List<PrixFilm>  findPrixFilmByCategorieMpTMP(int CatMp);
                
                public PrixFilm findPrixFilmByRegion(int idTfilm, int idGfilm,int idCg,int idInt);
}
