package dao.Manager;

import dao.Entity.RecuPromotionAccordee;
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
public interface RecuPromotionAccordeeDAO {
    
    public RecuPromotionAccordee findById(int id);
		
		public void add(RecuPromotionAccordee recuPromotionAccordee);
		
		public  RecuPromotionAccordee edit(RecuPromotionAccordee e);
		
		public  void delete(RecuPromotionAccordee e); 
		
		public List<RecuPromotionAccordee> findAll();
                
                public List<RecuPromotionAccordee>  findByEtat (String etat);
}