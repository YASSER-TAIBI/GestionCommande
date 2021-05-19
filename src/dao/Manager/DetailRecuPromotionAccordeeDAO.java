package dao.Manager;

import dao.Entity.DetailRecuPromotionAccordee;
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
public interface DetailRecuPromotionAccordeeDAO {
    
    public DetailRecuPromotionAccordee findById(int id);
		
		public void add(DetailRecuPromotionAccordee detailRecuPromotionAccordee);
		
		public  DetailRecuPromotionAccordee edit(DetailRecuPromotionAccordee e);
		
		public  void delete(DetailRecuPromotionAccordee e); 
		
		public List<DetailRecuPromotionAccordee> findAll();

                public List<DetailRecuPromotionAccordee>  findByRecuPromotionAccordee(int idRecuPromotionAccordee);
}