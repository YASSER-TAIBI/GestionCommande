package dao.Manager;

import dao.Entity.DetailPromotionAccordee;
import java.math.BigDecimal;
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
public interface DetailPromotionAccordeeDAO {
    
    public DetailPromotionAccordee findById(int id);
		
		public void add(DetailPromotionAccordee detailPromotionAccordee);
		
		public  DetailPromotionAccordee edit(DetailPromotionAccordee e);
		
		public  void delete(DetailPromotionAccordee e); 
		
		public List<DetailPromotionAccordee> findAll();
                
                public BigDecimal findByMontantPromoAcco(int mois);
                
                public List<DetailPromotionAccordee>  findByPromotionAccordee(int idPromotionAccordee);
                
                  public List<DetailPromotionAccordee>  findByPromotionAccordee(int idPromotionAccordee, String code);

}