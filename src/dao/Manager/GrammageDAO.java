/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Grammage;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface GrammageDAO {
      public Grammage findById(int id);
          
		public void add(Grammage grammage);
		
		
		public  Grammage edit(Grammage e);
		
		public  void delete(Grammage e); 
		
		public List<Grammage> findAll();

}
