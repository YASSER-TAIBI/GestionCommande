/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.ReferencePromo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface ReferencePromoDAO {
    
		
		public ReferencePromo findById(int id);
		
		public void add(ReferencePromo referencePromo);
		
		public  ReferencePromo edit(ReferencePromo e);
		
		public  void delete(ReferencePromo e); 
		
		public List<ReferencePromo> findAll();

                public ReferencePromo findByPrixOulmes(int prixOulmes);
}
