/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;


import dao.Entity.TauxDevise;
import java.util.List;

/**
 * 
 * @author Hp
 */
public interface TauxDeviseDAO {
   
		
                public TauxDevise findById(int id);
          
		public void add(TauxDevise TauxDevise);
		
		public  TauxDevise edit(TauxDevise e);
		
		public  void delete(TauxDevise e); 
		
		public List<TauxDevise> findAll();
                
                public TauxDevise findDeviseByTauxDevise(int idDevise);

}
