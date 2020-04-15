/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;


import dao.Entity.DetailReceptionEtrangere;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailReceptionEtrangereDAO {
    
               
                
      
    
		public DetailReceptionEtrangere findById(int id);
		
		public void add(DetailReceptionEtrangere detailReceptionEtrangere);
		
		
		public  DetailReceptionEtrangere edit(DetailReceptionEtrangere e);
		
		public  void delete(DetailReceptionEtrangere e); 
		
		public List<DetailReceptionEtrangere> findAll();
                
}
