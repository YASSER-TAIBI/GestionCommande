package dao.Manager;

import dao.Entity.PromotionAccordee;
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
public interface PromotionAccordeeDAO {
    
    public PromotionAccordee findById(int id);
		
		public void add(PromotionAccordee promotionAccordee);
		
		public  PromotionAccordee edit(PromotionAccordee e);
		
		public  void delete(PromotionAccordee e); 
		
		public List<PromotionAccordee> findAll();
                
                public List<String> findByClient();
                
                public List<PromotionAccordee> findByAllPromotionAccordee(String req);

}