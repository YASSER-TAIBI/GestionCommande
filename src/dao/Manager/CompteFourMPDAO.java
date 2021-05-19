/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CompteFourMP;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CompteFourMPDAO {
      
    
		public CompteFourMP findById(int id);
		
		public void add(CompteFourMP compteFourMP);
		
		public  CompteFourMP edit(CompteFourMP e);
		
		public  void delete(CompteFourMP e); 
		
		public List<CompteFourMP> findAll();
                
                public List<CompteFourMP> findByCodeCompteFourMP(String code);
		
         	public List<CompteFourMP> findBylibelleCompteFourMP(String lib);
}
