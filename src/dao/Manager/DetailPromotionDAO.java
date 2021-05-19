/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailPromotion;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailPromotionDAO {

		
		public DetailPromotion findById(int id);
		
		public void add(DetailPromotion detailPromotion);

		public  DetailPromotion edit(DetailPromotion e);
		
		public  void delete(DetailPromotion e); 
		
		public List<DetailPromotion> findAll();
                
               public BigDecimal findByMontantPromo(int mois);

}
