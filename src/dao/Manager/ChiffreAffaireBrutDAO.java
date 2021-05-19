/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.ChiffreAffaireBrut;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface ChiffreAffaireBrutDAO {
    
                public ChiffreAffaireBrut findById(int id);
		
		public void add(ChiffreAffaireBrut chiffreAffaireBrut);

		public  ChiffreAffaireBrut edit(ChiffreAffaireBrut e);
		
		public  void delete(ChiffreAffaireBrut e); 
		
		public List<ChiffreAffaireBrut> findAll();
                
                 public ChiffreAffaireBrut findByNumMois(int mois);
}
