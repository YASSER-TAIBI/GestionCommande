/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.BaremeTaux;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface BaremeTauxDAO {
    
                public BaremeTaux findById(int id);
		
		public void add(BaremeTaux baremeTaux);

		public  BaremeTaux edit(BaremeTaux e);
		
		public  void delete(BaremeTaux e); 
		
		public List<BaremeTaux> findAll();
                
                public BaremeTaux findByClasseTRO(BigDecimal tro);
}
