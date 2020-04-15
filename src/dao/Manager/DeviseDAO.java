/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Devise;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DeviseDAO {
   
		
                public Devise findById(int id);
          
		public void add(Devise Devise);
		
		public  Devise edit(Devise e);
		
		public  void delete(Devise e); 
		
		public List<Devise> findAll();


}
