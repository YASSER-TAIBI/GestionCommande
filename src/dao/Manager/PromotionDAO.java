package dao.Manager;

import dao.Entity.Promotion;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public interface PromotionDAO {
    
    public Promotion findById(int id);
		
		public void add(Promotion promotion);
		
		public  Promotion edit(Promotion e);
		
		public  void delete(Promotion e); 
		
		public List<Promotion> findAll();
                
                public Promotion findByNumCommandeAndNumLivraison(String numLivraison, String cmd);
}